package com.example.payment_qrcode.ui.screen.signin

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.listeners.OnDrawableClickListener
import com.example.payment_qrcode.utils.SingleLiveEvent
import com.example.payment_qrcode.utils.validateEmail
import com.example.payment_qrcode.utils.validatePassword
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _switchSignUpStatus = SingleLiveEvent<Boolean>().apply { value = false }
    val switchSignUpStatus: LiveData<Boolean>
        get() = _switchSignUpStatus

    private val _loginStatus = SingleLiveEvent<User>()
    val loginStatus: LiveData<User>
        get() = _loginStatus

    private val _loginTouchId = SingleLiveEvent<Boolean>().apply { value = false }
    val loginTouchId: LiveData<Boolean>
        get() = _loginTouchId

    var lvdErrorLogin = MutableLiveData<String>()
    var lvdEmail = object : MutableLiveData<String>() {
        override fun setValue(value: String?) {
            super.setValue(value)
            lvdErrorLogin.value = ""
        }
    }
    var lvdPassword = object : MutableLiveData<String>(){
        override fun setValue(value: String?) {
            super.setValue(value)
            lvdErrorLogin.value = ""
        }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                login()
            }

            R.id.btn_switch_sign_up -> {
                _switchSignUpStatus.value = true
            }
        }
    }

    fun onClickTouchId(view: View, @OnDrawableClickListener.DrawablePosition target: Int) {
        if (target == OnDrawableClickListener.DrawablePosition.RIGHT) {
            _loginTouchId.value = true
        }
    }

    private fun login() {
        _loginStatus.value =
            if (!lvdEmail.value.validateEmail() || !lvdPassword.value.validatePassword()) {
                User(email = "", password = "")
            } else {
                userRepository.getUserLogin(
                    email = lvdEmail.value!!.trim(),
                    password = lvdPassword.value
                )?.apply {
                    userRepository.saveUserInfo(this)
                } ?: kotlin.run {
                    User(email = "", password = "")
                }
            }
    }

}