package lee.bowon.holiday.service

import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.entity.LastUpdateDateInfo
import lee.bowon.holiday.enum.DataStorageType
import lee.bowon.holiday.repository.HolidayRepository
import lee.bowon.holiday.repository.LastUpdateDateInfoRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import java.util.logging.Level
import java.util.logging.Logger

@Service
class HolidayStorageService(
    private val holidayRepository: HolidayRepository,
    private val lastUpdateDateInfoRepository: LastUpdateDateInfoRepository) {

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
        Logger.getLogger("test").log(Level.INFO, "update run")
        if (isDataChanged(holidayList)) {
            holidayRepository.deleteAll()
            holidayRepository.saveAll(holidayList)
            lastUpdateDateInfoRepository.save(LastUpdateDateInfo(DataStorageType.HOLIDAY.name, LocalDateTime.now()))
        }
    }

    @Cacheable("holidays")
    fun getHolidays(): List<Holiday> {
        return holidayRepository.findAll()
    }

    fun getLastUpdate(): Optional<LastUpdateDateInfo> {
        return lastUpdateDateInfoRepository.findByTypeName(DataStorageType.HOLIDAY.name)
    }

    private fun isDataChanged(holidayList: List<Holiday>): Boolean {
        return holidayRepository.findAll() != holidayList
    }



}