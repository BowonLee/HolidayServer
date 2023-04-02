package lee.bowon.holiday.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime


/**
 * 각 정보들의 최종 업데이트 일자
 * 프론트에서 해당 정보를 서버와 동기화 시켜야 하는지 판단한다.
 */
@Entity
class LastUpdateDateInfo(
    @Column(unique = true) val typeName: String,
    @Column val updateDate: LocalDateTime
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long = 0

}