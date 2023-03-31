package lee.bowon.holiday.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls

import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.enum.DateKind
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class HolidayApiResponseWrapper(
    val response: HolidayApiResponse
)

data class HolidayApiResponse(
    val header: HolidayApiResponseHeader,
    val body: HolidayApiResponseBody
)

data class HolidayApiResponseHeader(
    val resultCode: String,
    val resultMsg: String
)

data class HolidayApiResponseBody(
    @JsonProperty(value = "items")
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    val items: HolidayApiResponseItems = HolidayApiResponseItems(),
    val numOfRows: Int = 0,
    val pageNo: Int = 0,
    val totalCount: Int = 0
)

data class HolidayApiResponseItems(
    val item: List<HolidayApiResponseItem> = listOf()
)

data class HolidayApiResponseItem(
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

data class HolidayApiResponseError(
    val cmmMsgHeader: HolidayApiResponseErrorItem
)

data class HolidayApiResponseErrorItem(
    val errMsg: String,
    val returnAuthMsg: String,
    val returnReasonCode: String,
)


