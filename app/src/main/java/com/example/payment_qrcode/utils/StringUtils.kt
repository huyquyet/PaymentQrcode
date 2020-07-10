package com.example.payment_qrcode.utils


fun String?.isTrimNullOrEmpty(): Boolean {
    return this?.trim()?.isEmpty() ?: true
}