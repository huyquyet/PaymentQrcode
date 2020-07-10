package com.example.payment_qrcode.data.repository

import androidx.annotation.IntDef
import androidx.annotation.StringDef

@StringDef(
    Currency.USD,
    Currency.EUR,
    Currency.GBP
)
annotation class Currency {
    companion object {
        const val USD = "USD"
        const val EUR = "EUR"
        const val GBP = "GBP"
    }
}
