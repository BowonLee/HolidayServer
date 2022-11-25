package lee.bowon.holiday.dto

import lee.bowon.holiday.enum.DateKind
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootTest
class XmlResponseTest {


    @Test
    fun parseHolidayTest() {
        val temp = XmlApiResponseItem(dateName = "개천절", dateKind = "01", isHoliday = "Y", locdate = "20221003", seq = 1 )

        Assertions.assertEquals(temp.toHoliday().isHoliday, true )
        Assertions.assertEquals(temp.toHoliday().date,  LocalDate.parse("20221003", DateTimeFormatter.BASIC_ISO_DATE))
        Assertions.assertEquals(temp.toHoliday().dateKind, DateKind.NATIONAL.kindName )
        Assertions.assertEquals(temp.toHoliday().dateName, "개천절")
    }
}