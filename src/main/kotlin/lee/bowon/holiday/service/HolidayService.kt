package lee.bowon.holiday.service

import kotlinx.coroutines.*
import lee.bowon.holiday.config.HolidayCacheConfig
import lee.bowon.holiday.dto.HolidayApiRequest
import lee.bowon.holiday.entity.Holiday
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.logging.Level
import java.util.logging.Logger

@Service
class HolidayService(
    private val holidayClient: HolidayClient,
    private val holidayStorageService: HolidayStorageService,
    private val cacheConfig: HolidayCacheConfig,
) {

    /**
     * http 호출하여 2년치 정보 저장
     * client는 한번에 한달만 조회 가능하기에 반복분을 사용
     *
     * 2트랙으로 나눔
     * 1. 데이터베이스에 정보 저장
     * 2. 캐시에 정보 저장
     */
    fun updateHolidayData() {
        kotlin.runCatching { updateListForTwoYear() }
    }

    /**
     * 휴일 정보를 가져온다.
     * 저장된 정보가 없을 시 데이터 셋팅 후 재시도 하도록 한다.
     */
    @Cacheable(HolidayCacheConfig.HOLIDAY_LIST_CACHE)
    fun getHolidayList(): List<Holiday> {
        val holidayList = holidayStorageService.getHolidays()

        return holidayList.ifEmpty {
            updateListForTwoYear()
        }
    }

    private fun updateListForTwoYear(): List<Holiday> {
        cacheConfig.cacheManager().getCache(HolidayCacheConfig.HOLIDAY_LIST_CACHE)?.clear()
        cacheConfig.cacheManager().getCache(HolidayCacheConfig.LAST_UPDATE_CACHE)?.clear()

        Logger.getLogger("test").log(Level.INFO, "update time : ${LocalDate.now()}")
        val nowYear = LocalDate.now().year
        val listForTwoYears = mutableSetOf<Holiday>()

        runBlocking {
            coroutineScope {
                (0..23).map {
                    async(Dispatchers.Default) {
                        listForTwoYears.addAll(
                            holidayClient.getHolidayData(
                                HolidayApiRequest(nowYear + it / 12, it % 12 + 1)
                            )
                        )
                    }
                }
            }.awaitAll()
            Logger.getLogger("test").log(Level.INFO, "request end ${listForTwoYears.toList()}")


            holidayStorageService.updateHolidayDataOfTwoYear(listForTwoYears.sortedBy { it.date })
        }


        return listForTwoYears.sortedBy { it.date }
    }

    private fun getHolidayListPerMonth(year: Int, month: Int): List<Holiday> =
        holidayClient.getHolidayData(HolidayApiRequest(year, month))

}