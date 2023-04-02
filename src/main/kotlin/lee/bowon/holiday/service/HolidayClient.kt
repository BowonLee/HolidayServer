package lee.bowon.holiday.service


import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import lee.bowon.holiday.constant.API_KEY
import lee.bowon.holiday.constant.END_POINT_URL
import lee.bowon.holiday.dto.*
import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.exception.HolidayApiRequestFailException
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

    fun getHolidayData(request: HolidayApiRequest): List<Holiday> {
        val rawResponse = requestData(request)

        return parseData(rawResponse.body!!).body.items.item.map {
            it.toHoliday()
        }
    }

    private fun parseData(response :String): HolidayApiResponse {
        if(isResponseSuccess(response)) {
            return parseSuccessData(response)
        } else {
            throw onRequestFail(response)
        }
    }

    private fun isResponseSuccess(json: String) :Boolean {
        return !json.contains("errMsg")
    }

    private fun parseSuccessData(json: String): HolidayApiResponse {
        val mapper = jacksonObjectMapper().registerKotlinModule()
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
        return mapper.readValue(json, HolidayApiResponseWrapper::class.java).response
    }

    /**
     * 에러 발생 시 xml 으로 반환된다.
     */
    private fun onRequestFail(xml: String): HolidayApiRequestFailException {
        val xmlDeserializer = XmlMapper(JacksonXmlModule().apply {
            setDefaultUseWrapper(false)
        }).registerKotlinModule()
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        return HolidayApiRequestFailException(xmlDeserializer.readValue(xml, HolidayApiResponseError::class.java))
    }


    private fun requestData(request: HolidayApiRequest): ResponseEntity<String> {
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

    private fun generateUri(request: HolidayApiRequest): URI{
        val httpBody: MultiValueMap<String, String> = LinkedMultiValueMap()

        httpBody["serviceKey"] = API_KEY
        httpBody["solYear"] = request.solYear.toString()
        httpBody["solMonth"] = request.solMonth.toString().padStart(2,'0')
        httpBody["_type"] = "json"

        return UriComponentsBuilder.fromHttpUrl(END_POINT_URL).queryParams(httpBody).build(true).toUri()
    }
}
