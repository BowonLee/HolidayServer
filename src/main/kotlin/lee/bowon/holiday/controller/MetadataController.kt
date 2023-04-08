package lee.bowon.holiday.controller

import lee.bowon.holiday.entity.LastUpdateDateInfo
import lee.bowon.holiday.service.MetaDataInfoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/meta")
class MetadataController(private val metaDataInfoService: MetaDataInfoService) {
    /**
     * 서버에서 받아야 하는 정보들의 최종 업데이트 일자
     */
    @PostMapping("updateDate")
    fun metaData(): List<LastUpdateDateInfo> {
        return metaDataInfoService.getLastUpdateInfoList()
    }
}