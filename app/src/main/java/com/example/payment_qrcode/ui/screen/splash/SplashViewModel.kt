package com.example.payment_qrcode.ui.screen.splash

import androidx.lifecycle.LiveData
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.utils.SingleLiveEvent
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _loginStatus = SingleLiveEvent<Boolean>().apply { value = false }

    val loginStatus: LiveData<Boolean>
        get() = _loginStatus

    fun checkLogin() {
        val userInfo = userRepository.getUserInfo()
        _loginStatus.value = !(userInfo?.email.isNullOrEmpty())
    }

    fun initData() {
        val listUser = userRepository.getListUserRegister()
        if (listUser == null || listUser.size == 0) {
            ArrayList<User>().apply {
                add(User(email = "quyet1@gmail.com", password = "12345678aA", firstName = "Nguyen Huy", lastName = "Quyet"))
                add(User(email = "quyet2@gmail.com", password = "12345678aA", firstName = "Nguyen Huy", lastName = "Quyet"))
                add(User(email = "quyet3@gmail.com", password = "12345678aA", firstName = "Nguyen Huy", lastName = "Quyet"))
                userRepository.saveListUserRegister(this)
            }
        }
    }
}