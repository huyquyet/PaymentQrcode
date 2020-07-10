package com.example.payment_qrcode.ui.screen.signup

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.utils.SingleLiveEvent
import com.example.payment_qrcode.utils.validateEmail
import com.example.payment_qrcode.utils.validatePassword
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _switchLoginStatus = SingleLiveEvent<Boolean>().apply { value = false }
    val switchLoginStatus: LiveData<Boolean>
        get() = _switchLoginStatus

    private val _signUpStatus = SingleLiveEvent<User>()
    val signUpStatus: LiveData<User>
        get() = _signUpStatus

    var lvdEmail = object : SingleLiveEvent<String>() {
        override fun setValue(value: String?) {
            value?.run {
                if (getValue() != this) {
                    checkEnabledButtonSignUp()
                }
            }
            super.setValue(value)
        }
    }

    var lvdPassword = object : SingleLiveEvent<String>() {
        override fun setValue(value: String?) {
            value?.run {
                if (getValue() != this) {
                    checkEnabledButtonSignUp()
                }
            }
            super.setValue(value)
        }
    }
    var lvdFirstName = SingleLiveEvent<String>()
    var lvdLastName = SingleLiveEvent<String>()

    var lvdEnabledBtnSignUp = MutableLiveData<Boolean>().apply { value = false }
    var lvdSwitchStatus = object : SingleLiveEvent<Boolean>() {
        override fun setValue(value: Boolean?) {
            super.setValue(value)
            value?.run {
                if (this) checkEnabledButtonSignUp()
            }
        }
    }.apply { value = false }

    fun setData() {
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_switch_login -> _switchLoginStatus.value = true
            R.id.btn_sign_up -> signUp()
            R.id.switch_policy -> lvdSwitchStatus.value = !lvdSwitchStatus.value!!
        }
    }

    private fun checkEnabledButtonSignUp() {
        lvdEnabledBtnSignUp.value = lvdSwitchStatus.value!!
                && validateEmailPassword()
    }

    private fun signUp() {
        if (!validateEmailPassword() || lvdSwitchStatus.value == null || !lvdSwitchStatus.value!!) return
        val email = lvdEmail.value!!.trim()
        val password = lvdPassword.value
        userRepository.checkUserRegister(email, password).also { isUser ->
            // isUser == true -> Email exists
            if (isUser) {
                _signUpStatus.value = null
            } else {
                val user = User(
                    email = password,
                    password = password,
                    firstName = lvdFirstName.value,
                    lastName = lvdLastName.value
                )
                if (userRepository.saveUserRegister(user)) {
                    _signUpStatus.value = user
                } else {
                    _signUpStatus.value = null
                }
            }
        }
    }

    private fun validateEmailPassword(): Boolean {
        return lvdEmail.value.validateEmail() && lvdPassword.value.validatePassword()
    }
}