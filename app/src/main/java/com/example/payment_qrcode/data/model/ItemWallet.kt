package com.example.payment_qrcode.data.model

import androidx.annotation.IntDef


data class ItemWallet(
    var id: Long,
    var name: String?,
    var description: String?,
    var price: Double?,
    var reason: String?,
    var imageUrl: Int = 0,
    @TypeItemWallet
    var type: Int = TypeItemWallet.ITEM,
    var date : String? = ""
)

@IntDef(
    TypeItemWallet.TIME,
    TypeItemWallet.ITEM
)
annotation class TypeItemWallet {
    companion object {
        const val TIME = 0
        const val ITEM = 1
    }
}