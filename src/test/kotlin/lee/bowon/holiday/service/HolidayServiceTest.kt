package lee.bowon.holiday.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HolidayServiceTest {

    @Autowired
    private lateinit var holidayService: HolidayService

    @Test
    fun updateTest() {
        holidayService.updateHolidayData()
        assert(false)
    }

}