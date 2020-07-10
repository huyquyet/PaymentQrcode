package com.example.payment_qrcode.data.model

import android.os.Parcelable
import com.example.payment_qrcode.data.repository.Currency
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Balance(
    var email: String,
    var balance: Double = 0.00,
    @Currency
    var currency: String? = Currency.USD
) : Parcelable