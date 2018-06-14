package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val compareYears = year.compareTo(other.year)
        val compareMonths = month.compareTo(other.month)
        val compareDays = dayOfMonth.compareTo(other.dayOfMonth)

        return if (compareYears == 0) if (compareMonths == 0) compareDays else compareMonths else
            compareYears
    }

}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate)