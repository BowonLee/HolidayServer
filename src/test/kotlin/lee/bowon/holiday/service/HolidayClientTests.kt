package lee.bowon.holiday.service

import lee.bowon.holiday.dto.HolidayHttpRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


import org.springframework.boot.test.context.SpringBootTest



@SpringBootTest
class HolidayClientTests {


    @Autowired
    private lateinit var holidayClient: HolidayClient

    @Test
    fun requestTest() {
        val request = HolidayHttpRequest("2022", "10")

        val result = this.holidayClient.getHolidayData(request)

        compareValues(result.toString(), "")
//        LoggerFactory.getILoggerFactory().getLogger().debug(request.)
//
//        compareValues(result, "")
    }
}