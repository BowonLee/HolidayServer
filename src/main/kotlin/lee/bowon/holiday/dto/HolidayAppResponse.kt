package lee.bowon.holiday.dto

import lee.bowon.holiday.entity.Holiday
import java.time.LocalDateTime

data class HolidayAppResponse(
    val lastUpdateTime: LocalDateTime?,
    val holidayList: List<Holiday>
)
