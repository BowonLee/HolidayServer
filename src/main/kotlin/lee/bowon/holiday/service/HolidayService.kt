package lee.bowon.holiday.service

import lee.bowon.holiday.repository.HolidayRepository
import org.springframework.stereotype.Service

@Service
class HolidayService(
    private val holidayRepository: HolidayRepository,
    private val holidayClient: HolidayClient
) {

    /**
     * http 호출하여 2년치 정보 저장
     * client는 한번에 한달만 조회 가능하기에
     */
    fun updateHolidayData() {

    }

    /**
     * 당일기준 2년치의 정보 획득
     * data source 에서 최대 2년동안의 데이터를 제공
     * 데이터 업데이트는 트랜젝션을 통해 일관성 유지
     */
    private fun storageHolidayDataOfTwoYear() {

    }

    private fun getHolidayDataPerMonth(year: Int, month: Int) {

    }


    /**
     *
     */

}