package lee.bowon.holiday.service

import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.repository.HolidayRepository
import org.springframework.stereotype.Service

@Service
class HolidayStorageService(private val holidayRepository: HolidayRepository) {

    /**
     * 당해기준 2년치의 정보 획득
     * data source 에서 최대 2년동안의 데이터를 제공
     * 데이터 업데이트는 트랜젝션을 통해 일관성 유지
     *
     * 변경사항이 있을 때만 업로드
     * 변경사항이 없느 경우 업로드 하지 않음
     */
    fun storageHolidayDataOfTwoYear(holidayList: List<Holiday>) {
        if (isDataChanged(holidayList)) {
            holidayRepository.deleteAll()
            holidayRepository.saveAll(holidayList)
        }
    }

    private fun isDataChanged(holidayList: List<Holiday>): Boolean {
        return holidayRepository.findAll() !== holidayList
    }


}