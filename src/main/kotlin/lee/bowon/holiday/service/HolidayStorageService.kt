package lee.bowon.holiday.service

import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.repository.HolidayRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.logging.Level
import java.util.logging.Logger

@Service
class HolidayStorageService(
    private val holidayRepository: HolidayRepository
) {

    /**
     * 당해기준 2년치의 정보 획득
     * data source 에서 최대 2년동안의 데이터를 제공
     * 데이터 업데이트는 트랜젝션을 통해 일관성 유지
     *
     * 변경사항이 있을 때만 업로드
     * 변경사항이 없는 경우 업로드 하지 않음
     */
    @Transactional
    fun updateHolidayDataOfTwoYear(holidayList: List<Holiday>) {
        if (isNeedUpdate(holidayList)) {
            Logger.getLogger("test").log(Level.INFO, "update run")
            holidayRepository.deleteAll()
            holidayRepository.saveAll(holidayList)
        }
    }


    fun getHolidays(): List<Holiday> {
        return holidayRepository.findAll()
    }

    private fun isNeedUpdate(holidayList: List<Holiday>): Boolean {
        val storedList = holidayRepository.findAll();

        if (storedList.isEmpty()) {
            return true
        }

        return storedList != holidayList
    }


}