package lee.bowon.holiday.service

import lee.bowon.holiday.entity.LastUpdateDateInfo
import lee.bowon.holiday.enum.DataStorageType
import lee.bowon.holiday.repository.LastUpdateDateInfoRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.jvm.optionals.getOrNull

@Service
class MetaDataInfoService(private val lastUpdateDateInfoRepository: LastUpdateDateInfoRepository) {

    fun getLastUpdateInfoList(): List<LastUpdateDateInfo> {
        return lastUpdateDateInfoRepository.findAll()
    }

    fun getHolidayUpdateDatetime(): LastUpdateDateInfo? {
        return lastUpdateDateInfoRepository.findByTypeName(DataStorageType.HOLIDAY.name)
    }
}