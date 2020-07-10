package com.example.payment_qrcode.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentSuccess(
    var id: Int,
    var transactionCode: String?,
    var transactionStatus: Boolean?
) : Parcelable