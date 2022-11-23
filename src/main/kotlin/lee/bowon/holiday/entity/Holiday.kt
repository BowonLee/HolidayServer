package lee.bowon.holiday.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id


@Entity
data class Holiday(
    @Id
    @Column(nullable = false)
    val date: Date,
    @Column(length = 10)
    val dateName: String,
    @Column(length = 5)
    val dateKind: String,
    val isHoliday: Boolean = true
    )


