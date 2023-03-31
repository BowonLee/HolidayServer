package lee.bowon.holiday.service

import lee.bowon.holiday.dto.HolidayRequest
import lee.bowon.holiday.entity.Holiday
import org.springframework.cache.CacheManager
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.logging.Level
import java.util.logging.Logger

@Service
class HolidayService(
    private val holidayClient: HolidayClient,
    private val holidayStorageService: HolidayStorageService,
    private val cacheManager: CacheManager
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
    fun getHolidayList(): List<Holiday> {
        val holidayList = holidayStorageService.getHolidays()

        return holidayList.ifEmpty {
            updateListForTwoYear()
        }
    }

    private fun updateListForTwoYear(): List<Holiday> {

        cacheManager.getCache("holidays")?.clear()
        Logger.getLogger("test").log(Level.INFO,"update time : ${LocalDate.now()}")
        var date = LocalDate.of(LocalDate.now().year,1,1)
        val listForTwoYears = mutableListOf<Holiday>()
        for (i in 1..24) {
            listForTwoYears.addAll(getHolidayListPerMonth(date.year, date.monthValue))
            date = date.plusMonths(1)
        }

        holidayStorageService.storageHolidayDataOfTwoYear(listForTwoYears)

        return listForTwoYears;
    }

    private fun getHolidayListPerMonth(year: Int, month: Int): List<Holiday>
            = holidayClient.getHolidayData(HolidayRequest(year,month)).map { it.toHoliday() }

}