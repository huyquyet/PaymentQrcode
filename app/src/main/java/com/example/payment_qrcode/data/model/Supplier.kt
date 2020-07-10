package com.example.payment_qrcode.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Supplier(
    var id: Int,
    var name: String?,
    var image: Int?

) : Parcelable