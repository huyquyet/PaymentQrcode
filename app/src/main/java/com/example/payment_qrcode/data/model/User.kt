package com.example.payment_qrcode.data.model

data class User(
    var email: String?,
    var password: String?,
    var firstName: String? = "",
    var lastName: String? = "",
    var birthday: Long? = 1
)