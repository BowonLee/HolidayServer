package lee.bowon.holiday.controller

import lee.bowon.holiday.entity.Holiday
import lee.bowon.holiday.service.HolidayService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/holiday")
class HolidayController(private val holidayService: HolidayService) {

    @PostMapping("list")
    fun holidayList(): List<Holiday> {
        return holidayService.getHolidayList()
    }

    /**
     * 강제로 데이터를 업데이트 해야 하는 경우
     */
    @PutMapping("update")
    fun update() {
        holidayService.updateHolidayData()
    }

    /**
     * 서버에서 받아야 하는 정보들의 최종 업데이트 일자
     */
    @PostMapping("meta")
    fun metaData() {

    }
}