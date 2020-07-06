package com.example.payment_qrcode.data.model

data class User(
    var id: Int?,
    var email: String?,
    var password: String?,
    var first_name: String?,
    var last_name: String?,
    var birthday: Long?
)