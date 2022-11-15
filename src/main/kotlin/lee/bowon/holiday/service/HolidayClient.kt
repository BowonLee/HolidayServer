package lee.bowon.holiday.service

import lee.bowon.holiday.dto.HolidayHttpRequest
import lee.bowon.holiday.dto.HolidayHttpResponse
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service

@Service
class HolidayClient {
//    val restClient = RestClient
    val httpHeaders = HttpHeaders()
//    vla
    constructor()

    public fun getHolidayData(request: HolidayHttpRequest): HolidayHttpResponse {

    }
}
