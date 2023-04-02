package lee.bowon.holiday.service

import lee.bowon.holiday.entity.LastUpdateDateInfo
import lee.bowon.holiday.repository.LastUpdateDateInfoRepository


class MetaDataInfoService(private val lastUpdateDateInfoRepository: LastUpdateDateInfoRepository) {

    fun getLastUpdateInfoList(): List<LastUpdateDateInfo> {
        return lastUpdateDateInfoRepository.findAll()
    }
}