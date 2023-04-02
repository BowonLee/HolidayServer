package lee.bowon.holiday.service

import lee.bowon.holiday.dto.HolidayApiRequest
import lee.bowon.holiday.dto.HolidayApiResponseItem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


import org.springframework.boot.test.context.SpringBootTest



@SpringBootTest
class HolidayClientTests {


    @Autowired
    private lateinit var holidayClient: HolidayClient

    @Test
    fun response_empty() {
        val request = HolidayApiRequest(2023, 1)

        val result = this.holidayClient.getHolidayData(request)

        assert(result.isEmpty())
    }

    @Test
    fun response_ok() {
        val request = HolidayApiRequest(2022, 10)

        val result = this.holidayClient.getHolidayData(request)

        val actual = HolidayApiResponseItem(dateName = "개천절", dateKind = "01", isHoliday = "Y", locdate = "20221003", seq = 1 )

        assertEquals(result[0],actual)
        assertEquals(result.size , 3)
    }
}