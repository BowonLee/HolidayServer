package lee.bowon.holiday.dto

import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.enum.DateKind
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
) {
    fun toHoliday() : Holiday {
        return Holiday(
            date = LocalDate.parse(locdate, DateTimeFormatter.BASIC_ISO_DATE),
            dateName = dateName,
            dateKind = DateKind.values().find { it.code == dateKind }?.kindName ?: DateKind.ETC,
            isHoliday = (isHoliday == "Y"))
    }
}

data class XmlApiResponseError(
    val cmmMsgHeader: XmlApiResponseErrorItem
)

data class XmlApiResponseErrorItem(
    val errMsg: String,
    val returnAuthMsg: String,
    val returnReasonCode: String,
)


