package com.example.payment_qrcode.listeners

interface OnItemCLick<T> {
    fun onItemClick(item: T?, position: Int)
}