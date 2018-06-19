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

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> {
        return DateIterator(start, endInclusive)
    }

    operator fun contains(d: MyDate) : Boolean {
        return d >= start && d <= endInclusive
    }
}

class DateIterator(val start: MyDate, val endInclusive: MyDate) : Iterator<MyDate> {

    var current = start

    override fun hasNext(): Boolean {
        return current <= endInclusive
    }

    override fun next(): MyDate {
        return current.apply {
            current = current.nextDay()
        }
    }

}