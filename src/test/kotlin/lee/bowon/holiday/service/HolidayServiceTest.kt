package lee.bowon.holiday.service


import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HolidayServiceTest {

    @Autowired
    private lateinit var holidayService: HolidayService

    @BeforeEach
    fun initData() {
        holidayService.updateHolidayData()
    }

    @Test
    fun getTest() {
        holidayService.getHolidayList()
        holidayService.getHolidayList()
        holidayService.getHolidayList()
    }

}