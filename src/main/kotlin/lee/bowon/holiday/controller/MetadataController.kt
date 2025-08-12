package lee.bowon.holiday.controller

import lee.bowon.holiday.entity.LastUpdateDateInfo
import lee.bowon.holiday.service.MetaDataStorageService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/meta")
class MetadataController(private val metaDataStorageService: MetaDataStorageService) {
    /**
     * 서버에서 받아야 하는 정보들의 최종 업데이트 일자
     */
    @Operation(
        summary = "Get metadata update dates",
        description = "Returns last update timestamps for various server data"
    )
    @PostMapping("updateDate")
    fun metaData(): List<LastUpdateDateInfo> {
        return metaDataStorageService.getLastUpdateInfoList()
    }
}
