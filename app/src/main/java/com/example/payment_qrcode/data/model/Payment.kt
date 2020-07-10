package com.example.payment_qrcode.data.model

import android.os.Parcelable
import com.example.payment_qrcode.data.repository.Currency
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Payment(
    var id: Int,
    var name: String? = "",
    @Currency
    var currency: String? = Currency.USD,
    var total: Double? = 0.00,
    @SerializedName("sub_total")
    var subTotal: Double? = 0.00,
    var fee: Double? = 0.00,
    @SerializedName("payment_method")
    var paymentMethod: PaymentMethod?,
    var supplier: Supplier?
) : Parcelable