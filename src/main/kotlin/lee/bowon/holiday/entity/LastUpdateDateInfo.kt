package lee.bowon.holiday.entity

import jakarta.persistence.*
import java.time.LocalDate


/**
 * 각 정보들의 최종 업데이트 일자
 * 프론트에서 해당 정보를 서버와 동기화 시켜야 하는지 판단한다.
 */
@Entity
class LastUpdateDateInfo(
    @Column val name: String,
    @Column val updateDate: LocalDate
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long = 0

}