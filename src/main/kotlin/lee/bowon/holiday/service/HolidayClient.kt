package lee.bowon.holiday.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import lee.bowon.holiday.constant.API_KEY
import lee.bowon.holiday.constant.END_POINT_URL
import lee.bowon.holiday.dto.XmlApiRequest
import lee.bowon.holiday.dto.XmlApiResponseError
import lee.bowon.holiday.dto.XmlApiResponse
import lee.bowon.holiday.dto.XmlApiResponseItem
import lee.bowon.holiday.exception.XmlApiRequestFailException
import org.springframework.http.*
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.net.URI
import java.nio.charset.StandardCharsets

/**
 * 공식 API 센터를 통해 휴일 정보를 받아온다.
 */
@Service
class HolidayClient {

    fun getHolidayData(request: XmlApiRequest): List<XmlApiResponseItem> {
        val rawResponse = requestData(request)

        return parseData(rawResponse.body!!).body.items.item
    }

    private fun parseData(xml :String): XmlApiResponse {
        if(isResponseSuccess(xml)) {
            return parseSuccessData(xml)
        } else {
            throw onResponseFail(xml)
        }

    }

    private fun isResponseSuccess(xml: String) :Boolean {
        return !xml.contains("errMsg")
    }

    private fun parseSuccessData(xml: String): XmlApiResponse {
        val mapper = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return mapper.readValue(xml, XmlApiResponse::class.java)
    }

    private fun onResponseFail(xml: String): Throwable {
        val mapper = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val result = mapper.readValue(xml, XmlApiResponseError::class.java)

        throw XmlApiRequestFailException(result)
    }

    private fun requestData(request: XmlApiRequest): ResponseEntity<String> {
        val restTemplate = RestTemplate()
        val httpHeaders = getHeader()
        val uri = generateUri(request)
        val httpEntity = HttpEntity<HttpHeaders>(httpHeaders)

        restTemplate.messageConverters.add(0,StringHttpMessageConverter(StandardCharsets.UTF_8))
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String::class.java)
    }

    private fun getHeader(): HttpHeaders {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        return httpHeaders

    }

    private fun generateUri(request: XmlApiRequest): URI{
        val httpBody: MultiValueMap<String, String> = LinkedMultiValueMap()

        httpBody["serviceKey"] = API_KEY
        httpBody["solYear"] = request.solYear.toString()
        httpBody["solMonth"] = request.solMonth.toString().padStart(2,'0')

        return UriComponentsBuilder.fromHttpUrl(END_POINT_URL).queryParams(httpBody).build(true).toUri()
    }
}
