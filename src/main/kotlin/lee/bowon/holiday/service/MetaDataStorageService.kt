package lee.bowon.holiday.service

import lee.bowon.holiday.config.CustomCacheConfig
import lee.bowon.holiday.entity.LastUpdateDateInfo
import lee.bowon.holiday.enum.DataStorageType
import lee.bowon.holiday.repository.LastUpdateDateInfoRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class MetaDataStorageService(
    private val lastUpdateDateInfoRepository: LastUpdateDateInfoRepository
) {

    fun updateHolidayUpdateDateTime() {
        lastUpdateDateInfoRepository.save(LastUpdateDateInfo(DataStorageType.HOLIDAY.name, LocalDateTime.now()))
    }

    @Cacheable(CustomCacheConfig.LAST_UPDATE_DATE_LIST_CACHE)
    fun getLastUpdateInfoList(): List<LastUpdateDateInfo> {
        return lastUpdateDateInfoRepository.findAll()
    }

    @Cacheable(CustomCacheConfig.LAST_UPDATE_CACHE)
    fun getHolidayUpdateDatetime(): LastUpdateDateInfo? {
        return lastUpdateDateInfoRepository.findByTypeName(DataStorageType.HOLIDAY.name)
    }
}