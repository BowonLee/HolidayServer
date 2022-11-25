package lee.bowon.holiday.service

import lee.bowon.holiday.dto.XmlApiRequest
import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.repository.HolidayRepository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HolidayService(
    private val holidayClient: HolidayClient,
    private val holidayStorageService: HolidayStorageService
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
        updateListForTwoYear()
    }

    /**
     * 휴일 정보를 가져온다.
     * 1. 캐시정보 조회하여 획득
     * 2. 캐시가 없을 시 데이터베이스 획득
     * 3. 정보반환 후 해당 정보 캐시 등록
     */
    fun getHolidayList() {

    }

    private fun updateListForTwoYear() {

        var date = LocalDate.of(LocalDate.now().year,1,1)
        val listForTwoYears = mutableListOf<Holiday>()
        for (i in 1..24) {
            listForTwoYears.addAll(getHolidayListPerMonth(date.year, date.monthValue))
            date = date.plusMonths(1)
        }

        holidayStorageService.storageHolidayDataOfTwoYear(listForTwoYears)
        updateDataToCache()
    }


    private fun updateDataToCache() {

    }

    private fun getHolidayListPerMonth(year: Int, month: Int): List<Holiday>
            = holidayClient.getHolidayData(XmlApiRequest(year,month)).map { it.toHoliday() }

}