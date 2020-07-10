package com.example.payment_qrcode.ui.screen.scan

import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.repository.UserRepository
import javax.inject.Inject

class ScanViewModel @Inject constructor(val userRepository: UserRepository) : BaseViewModel() {
}