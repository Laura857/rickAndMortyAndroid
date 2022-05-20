package com.formation.app.common.extensions

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Date.getBeginningOfDay(): Date {
    val startTime: Calendar = Calendar.getInstance()
    startTime.time = this
    startTime.set(Calendar.HOUR_OF_DAY, 0)
    startTime.set(Calendar.MINUTE, 0)
    startTime.set(Calendar.SECOND, 0)
    startTime.set(Calendar.MILLISECOND, 0)
//    if (startTime.time < Date()) {
//        startTime.time = Date()
//    }
    return startTime.time
}

fun Date.startOfWeek(): Date {
    val startTime: Calendar = Calendar.getInstance()
    startTime.time = this
    startTime.set(Calendar.DAY_OF_WEEK, startTime.firstDayOfWeek)
    startTime.set(Calendar.HOUR_OF_DAY, 0)
    startTime.set(Calendar.MINUTE, 0)
    startTime.set(Calendar.SECOND, 0)
    startTime.set(Calendar.MILLISECOND, 0)
    return startTime.time
}

fun Date.endOfWeek(): Date {
    return this.startOfWeek().addDays(6)
}

fun Date.isSameDay(other: Date): Boolean {
    val fmt = SimpleDateFormat("yyyyMMdd")
    return fmt.format(this) == fmt.format(other)
}

fun Date.diffBetween(other: Date, inUnit: TimeUnit = TimeUnit.DAYS): Long {
    val diff = this.time - other.time
    return inUnit.convert(diff, TimeUnit.MILLISECONDS)
}

fun Date.setHourAndMinute(other: Date): Date {
    val cDay = Calendar.getInstance()
    val cHour = Calendar.getInstance()
    cDay.time = this
    cHour.time = other
    cDay.set(Calendar.HOUR_OF_DAY, cHour.get(Calendar.HOUR_OF_DAY))
    cDay.set(Calendar.MINUTE, cHour.get(Calendar.MINUTE))
    return cDay.time
}

fun Date.getNextDay(): Date {
    val endTime: Calendar = Calendar.getInstance()
    endTime.time = this
    endTime.add(Calendar.DATE, 1)
    return endTime.time
}

fun Date.getYesterday(): Date {
    val endTime: Calendar = Calendar.getInstance()
    endTime.time = this
    endTime.add(Calendar.DATE, -1)
    return endTime.time
}

/**
 * Pattern: yyyy-MM-dd HH:mm:ss
 */
fun Date.formatToServerDateTimeDefaults(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

fun Date.formatToTruncatedDateTime(): String {
    val sdf = SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: yyyy-MM-dd
 */
fun Date.formatToServerDateDefaults(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToServerTimeDefaults(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy HH:mm:ss
 */
fun Date.formatToViewDateTimeDefaults(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: dd/MM/yyyy
 */
fun Date.formatToViewDateDefaults(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(this)
}

/**
 * Pattern: HH:mm:ss
 */
fun Date.formatToViewTimeDefaults(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(this)
}

fun Date.stringWithPattern(pattern: String): String {
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    return sdf.format(this)
}

/**
 * Add field date to current date
 */
fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        time = this@add
        add(field, amount)
        return time
    }
}

fun Date.addYears(years: Int): Date {
    return add(Calendar.YEAR, years)
}
fun Date.addMonths(months: Int): Date {
    return add(Calendar.MONTH, months)
}
fun Date.addDays(days: Int): Date {
    return add(Calendar.DAY_OF_MONTH, days)
}
fun Date.addHours(hours: Int): Date {
    return add(Calendar.HOUR_OF_DAY, hours)
}
fun Date.addMinutes(minutes: Int): Date {
    return add(Calendar.MINUTE, minutes)
}
fun Date.addSeconds(seconds: Int): Date {
    return add(Calendar.SECOND, seconds)
}
