package lee.bowon.holiday

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HolidayApplication

fun main(args: Array<String>) {
	runApplication<HolidayApplication>(*args)
}
