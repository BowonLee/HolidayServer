package lee.bowon.holiday.dto

/**
 * 월별 휴일 요청값
 */
data class HolidayHttpRequest(
    val solYear: String,
    val solMonth:String
)
