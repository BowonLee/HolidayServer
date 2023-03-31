//package lee.bowon.holiday.entity
//
//import jakarta.persistence.Column
//import jakarta.persistence.Entity
//import java.time.LocalDate
//
//
///**
// * 각 정보들의 최종 업데이트 일자
// * 프론트에서 해당 정보를 서버와 동기화 시켜야 하는지 판단한다.
// */
//@Entity
//class LastUpdateDateInfo(
//    holidayUpdateDateTime: LocalDate
//) {
//    @Column
//    val holiday: LocalDate = holidayUpdateDateTime
//}