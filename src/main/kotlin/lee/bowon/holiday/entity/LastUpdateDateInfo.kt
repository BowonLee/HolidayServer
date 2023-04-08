package lee.bowon.holiday.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime


/**
 * 각 정보들의 최종 업데이트 일자
 * 프론트에서 해당 정보를 서버와 동기화 시켜야 하는지 판단한다.
 */
@Entity
class LastUpdateDateInfo(
    @Id
    @Column(unique = true) val typeName: String,
    @Column val updateDateTime: LocalDateTime
) {

}