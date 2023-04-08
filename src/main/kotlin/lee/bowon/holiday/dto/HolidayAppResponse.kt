package lee.bowon.holiday.dto

import io.swagger.annotations.ApiParam
import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.entity.LastUpdateDateInfo
import java.time.LocalDateTime

data class HolidayAppResponse(
    @ApiParam(value = "최종 업데이트 일자")
    val lastUpdateTime: LocalDateTime?,
    @ApiParam(value = "휴일 리스트")
    val holidayList: List<Holiday>
)
