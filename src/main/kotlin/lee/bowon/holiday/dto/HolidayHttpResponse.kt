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
    val items: HolidayResponseItems = HolidayResponseItems(),
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class HolidayResponseItems(
    val item: List<HolidayResponseItem> = listOf()
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


