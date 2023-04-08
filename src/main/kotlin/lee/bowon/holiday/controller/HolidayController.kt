package lee.bowon.holiday.controller

import lee.bowon.holiday.dto.HolidayAppResponse
import lee.bowon.holiday.service.HolidayService
import lee.bowon.holiday.service.MetaDataInfoService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/holiday")
class HolidayController(
    private val holidayService: HolidayService,
    private val metaDataInfoService: MetaDataInfoService
) {

    @PostMapping("list")
    fun holidayList(): HolidayAppResponse {

        return HolidayAppResponse(
            holidayList = holidayService.getHolidayList(),
            lastUpdateTime = metaDataInfoService.getHolidayUpdateDatetime()?.updateDate
        )
    }

    /**
     * 강제로 데이터를 업데이트 해야 하는 경우
     */
    @PutMapping("update")
    fun update() {
        holidayService.updateHolidayData()
    }
}