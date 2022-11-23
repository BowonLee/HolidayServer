package lee.bowon.holiday.enum

/**
 * 국경일(01) - 광복절, 개천절 ...
 * 기념일(02) - 4.19, 5.18 ...
 * 24절기(03) - 하지,동지 ..
 * 잡절(04) - 단오,한식 .. (24절기에 속하지 않는 절기)
 */
enum class DateKind(val code:String, val kindName: String) {
    NATIONAL("01", "국경일") ,
    ANNIVERSARY("02", "기념일") ,
    MAIN_SEASONAL_DAYS("03", "24절기"),
    SUB_SEASONAL_DAYS("04", "잡절");
    companion object{
        const val ETC = "기타"
    }
}

