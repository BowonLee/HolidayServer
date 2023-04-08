package lee.bowon.holiday.service

import lee.bowon.holiday.config.HolidayCacheConfig
import lee.bowon.holiday.entity.LastUpdateDateInfo
import lee.bowon.holiday.enum.DataStorageType
import lee.bowon.holiday.repository.LastUpdateDateInfoRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MetaDataInfoService(private val lastUpdateDateInfoRepository: LastUpdateDateInfoRepository) {

    fun getLastUpdateInfoList(): List<LastUpdateDateInfo> {
        return lastUpdateDateInfoRepository.findAll()
    }

    @Cacheable(HolidayCacheConfig.LAST_UPDATE_CACHE)
    fun getHolidayUpdateDatetime(): LastUpdateDateInfo? {
        return lastUpdateDateInfoRepository.findByTypeName(DataStorageType.HOLIDAY.name)
    }
}