package lee.bowon.holiday.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import lee.bowon.holiday.constant.API_KEY
import lee.bowon.holiday.constant.END_POINT_URL
import lee.bowon.holiday.dto.HolidayHttpRequest
import lee.bowon.holiday.dto.HolidayHttpResponseError
import lee.bowon.holiday.dto.HolidayResponse
import lee.bowon.holiday.exception.HolidayAPIRequestFailException
import org.apache.juli.logging.Log
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.*
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI

/**
 * 공식 API 센터를 통해 휴일 정보를 받아온다.
 */
@Service
class HolidayClient() {

    fun getHolidayData(request: HolidayHttpRequest): HolidayResponse? {

        val rawResponse = requestData(request)

        return parseData(rawResponse.body!!)
    }

    private fun parseData(xml :String): HolidayResponse? {
        if(isResponseSuccess(xml)) {
            return parseSuccessData(xml)
        } else {
            throw onResponseFail(xml)
        }

    }

    private fun isResponseSuccess(xml: String) :Boolean {
        return xml in "errorMsg"
    }

    private fun parseSuccessData(xml: String): HolidayResponse? {
        val mapper = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        println(xml)
        return mapper.readValue(xml, HolidayResponse::class.java)
    }

    private fun onResponseFail(xml: String): Throwable {
        val mapper = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        println(xml)
        val result = mapper.readValue(xml, HolidayHttpResponseError::class.java)

        throw HolidayAPIRequestFailException(result)
    }

    private fun requestData(request: HolidayHttpRequest): ResponseEntity<String> {
        val restTemplate = RestTemplate()
        val httpHeaders = getHeader()
        val url = generateUrl(request)
        val httpEntity = HttpEntity<HttpHeaders>(httpHeaders)

        println(url)

        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String::class.java )

//        return restTemplate.getForEntity(url, String::class.java, httpEntity)
    }

    private fun getHeader(): HttpHeaders {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        return httpHeaders

    }

    private fun generateUrl(request: HolidayHttpRequest): String{
        val httpBody: MultiValueMap<String, String> = LinkedMultiValueMap()

        httpBody["serviceKey"] = API_KEY
        httpBody["solYear"] = request.solYear
        httpBody["solMonth"] = request.solMonth


        return UriComponentsBuilder.fromHttpUrl(END_POINT_URL).queryParams(httpBody).build(true).toUriString()
    }
}
