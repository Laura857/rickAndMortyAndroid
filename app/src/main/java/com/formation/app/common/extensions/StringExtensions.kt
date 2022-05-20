package com.formation.app.common.extensions

import android.text.TextUtils
import java.text.SimpleDateFormat
import java.util.*

fun String.parseDate(pattern: String): Date? {
    return try {
        SimpleDateFormat(pattern).parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.toPrice(): Int {
    return try {
        (this.replace(',', '.').toFloat() * 100).toInt()
    } catch (e: Exception) {
        0
    }
}

fun String.isNameValid(): Boolean {
    return this.isNotEmpty()
}

fun String.isPasswordValid(): Boolean {
    return this.isLength(8) && this.hasNb() && this.hasLowercase() && this.hasUppercase() && this.hasSymbol()
}

fun String.isPhoneValid(): Boolean {
    return this.isLength(7) && this.hasNb()
}

fun String.isLength(nb: Int): Boolean {
    return this.length >= 8
}

fun String.hasNb(): Boolean {
    val regex = """^(?=.*\d)[0-9a-zA-Z!@#$%^&*()\-_=+{}|?>.<,:;~`’]{1,}$""".toRegex()
    return regex.matches(this)
}

fun String.hasLowercase(): Boolean {
    val regex = """^(?=.*[a-z])[0-9a-zA-Z!@#$%^&*()\\-_=+{}|?>.<,:;~`’]{1,}$""".toRegex()
    return regex.matches(this)
}

fun String.hasUppercase(): Boolean {
    val regex = """^(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*()\\-_=+{}|?>.<,:;~`’]{1,}$""".toRegex()
    return regex.matches(this)
}

fun String.hasSymbol(): Boolean {
    val regex = """^(?=.*[!@#$%^&*()\\-_=+{}|?>.<,:;~`’])[0-9a-zA-Z!@#$%^&*()\\-_=+{}|?>.<,:;~`’]{1,}$""".toRegex()
    return regex.matches(this)
}

fun String.removeWhiteSpace() : String {
    return this.filter { it1 -> !it1.isWhitespace() }
}