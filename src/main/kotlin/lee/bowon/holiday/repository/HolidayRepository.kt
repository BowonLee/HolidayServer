package lee.bowon.holiday.repository;

import lee.bowon.holiday.entity.Holiday
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface HolidayRepository : JpaRepository<Holiday, Date> {
}