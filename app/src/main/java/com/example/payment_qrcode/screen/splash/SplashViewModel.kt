package com.example.payment_qrcode.screen.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.repository.UserRepository
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _loginStatus = MutableLiveData<Boolean>().apply { value = false }

    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    fun checkLogin() {
        val userInfo = userRepository.getUserInfo()
        _loginStatus.value = !(userInfo?.id == null || userInfo.id!! <= 0)
    }
}