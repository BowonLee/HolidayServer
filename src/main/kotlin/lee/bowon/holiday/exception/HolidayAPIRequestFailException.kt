package lee.bowon.holiday.exception

import lee.bowon.holiday.dto.HolidayHttpResponseError

/**
 * API 요청 반환값이 오류일 경우 발생
 */
class HolidayAPIRequestFailException: Exception {

//    constructor(): super()
    constructor(message: String): super(message)
    constructor(error: HolidayHttpResponseError): this(error.cmmMsgHeader.errMsg) {
        println(error)
    }

}