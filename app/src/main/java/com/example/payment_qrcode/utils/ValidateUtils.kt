package com.example.payment_qrcode.utils

import android.util.Patterns
import java.util.regex.Pattern

const val PATTEN_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"

private fun checkRegexMatching(str: String, regexStr: String): Boolean {
    val p: Pattern = createPattern(regexStr)
    val m = p.matcher(str)
    return m.find()
}

private fun checkRegexMatching(str: String, pattern: Pattern): Boolean {
    return pattern.matcher(str).find()
}

fun createPattern(pattern: String): Pattern {
    return Pattern.compile(pattern)
}

fun String?.validateEmail(): Boolean {
    return this?.let {
        return checkRegexMatching(this, Patterns.EMAIL_ADDRESS)
    } ?: false
}

fun String?.validatePassword(): Boolean {
    return this?.let {
        return checkRegexMatching(this, PATTEN_PASSWORD)
    } ?: false
}

