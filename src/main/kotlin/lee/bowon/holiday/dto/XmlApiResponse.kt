package lee.bowon.holiday.dto


data class XmlApiResponse(
    val header: XmlApiResponseHeader,
    val body: XmlApiResponseBody
)

data class XmlApiResponseHeader(
    val resultCode: String,
    val resultMsg: String
)

data class XmlApiResponseBody(
    val items: XmlApiResponseItems = XmlApiResponseItems(),
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)

data class XmlApiResponseItems(
    val item: List<XmlApiResponseItem> = listOf()
)

data class XmlApiResponseItem(
    val dateKind: String,
    val dateName: String,
    val isHoliday: String,
    val locdate: String,
    val seq: Int
)

data class XmlApiResponseError(
    val cmmMsgHeader: XmlApiResponseErrorItem
)

data class XmlApiResponseErrorItem(
    val errMsg: String,
    val returnAuthMsg: String,
    val returnReasonCode: String,
)


