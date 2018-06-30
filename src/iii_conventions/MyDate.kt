package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        val compareYears = year.compareTo(other.year)
        val compareMonths = month.compareTo(other.month)
        val compareDays = dayOfMonth.compareTo(other.dayOfMonth)

        return if (compareYears == 0) if (compareMonths == 0) compareDays else compareMonths else
            compareYears
    }

    val daysInMonth = when(this.month){
        0 -> 31
        1 -> 28
        2 -> 31
        3 -> 30
        4  -> 31
        5 -> 30
        6 -> 31
        7 -> 31
        8 ->30
        9 -> 31
        10 -> 30
        else -> 31
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(timeInterval: TimeInterval) : MyDate {
    return when(timeInterval) {
        TimeInterval.DAY -> {
            if (daysInMonth == dayOfMonth) {
                MyDate(year, month + 1, 1)
            } else {
                MyDate(year, month, dayOfMonth + 1)
            }
        }
        TimeInterval.WEEK -> {
            if (daysInMonth < dayOfMonth + 7) {
                MyDate(year, month + 1, 7 - (daysInMonth - dayOfMonth))
            } else {
                MyDate(year, month, dayOfMonth + 7)
            }
        }
        TimeInterval.YEAR -> MyDate(year + 1, month, dayOfMonth)
    }
}

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval) : MyDate {
    return when(repeatedTimeInterval.ti) {
        TimeInterval.DAY -> {
            var date = this

            for (i in 0 .. repeatedTimeInterval.n - 1) {
                date = date + TimeInterval.DAY
            }

            return date
        }
        TimeInterval.WEEK -> {
            var date = this

            for (i in 0 .. repeatedTimeInterval.n - 1) {
                date = date + TimeInterval.WEEK
            }

            return date
        }
        TimeInterval.YEAR -> MyDate(this.year + repeatedTimeInterval.n, this.month, this.dayOfMonth)
    }
}

operator fun TimeInterval.times(n: Int) : RepeatedTimeInterval {
    return RepeatedTimeInterval(this, n)
}

data class RepeatedTimeInterval(val ti: TimeInterval, val n: Int)

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