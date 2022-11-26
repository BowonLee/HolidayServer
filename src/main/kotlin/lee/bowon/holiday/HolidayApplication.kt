package lee.bowon.holiday

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class HolidayApplication

fun main(args: Array<String>) {
	runApplication<HolidayApplication>(*args)
}
