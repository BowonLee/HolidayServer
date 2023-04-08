package lee.bowon.holiday.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDate


@Entity
class Holiday(
    @Id
    @Column(nullable = false)
    val date: LocalDate,
    @Column(length = 20)
    val dateName: String,
    @Column(length = 10)
    val dateKind: String,
    val isHoliday: Boolean = true
)


