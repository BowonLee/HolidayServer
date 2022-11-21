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
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class Items(
    val item: List<HolidayResponseItem>
)

data class HolidayResponseItem(
    val dateKind: String,
    val dateName: String,
    val isHoliday: String,
    val locdate: String,
    val seq: Int
)

data class HolidayHttpResponseError(
    val cmmMsgHeader: HolidayHttpResponseErrorItem
)

data class HolidayHttpResponseErrorItem(
    val errMsg: String,
    val returnAuthMsg: String,
    val returnReasonCode: String,

    )


