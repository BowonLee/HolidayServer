package lee.bowon.holiday.exception

import lee.bowon.holiday.dto.HolidayApiResponseError

/**
 * API 요청 반환값이 오류일 경우 발생
 */
class HolidayApiRequestFailException: Exception {

    constructor(message: String): super(message)
    constructor(error: HolidayApiResponseError): this(error.cmmMsgHeader.errMsg) {
        println(error)
    }
}