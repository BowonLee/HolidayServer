package lee.bowon.holiday.dto


data class HolidayResponse(
    val header: HolidayResponseHeader,
    val body: HolidayResponseBody
)

data class HolidayResponseHeader(
    val resultCode: String,
    val resultMsg: String
)

data class HolidayResponseBody(
    val items: Array<HolidayResponseItem>,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class HolidayResponseItem(
    val dataKind: String,
    val isHoliday: String,
    val locdate: String,
    val seq: Int
)

