package lee.bowon.holiday.dto

/**
 * 월별 휴일 요청값
 */
data class HolidayApiRequest(
    val solYear: Int,
    val solMonth:Int
)
