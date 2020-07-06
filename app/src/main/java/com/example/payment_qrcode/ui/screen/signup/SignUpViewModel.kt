package com.example.payment_qrcode.ui.screen.signup

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.utils.SingleLiveEvent
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject

class SignUpViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _signUpStatus = SingleLiveEvent<Boolean>().apply { value = false }
    val signUpStatus: LiveData<Boolean>
        get() = _signUpStatus

    var lvdEmail = MutableLiveData<String>().apply { value = "" }
    var lvdPassword = MutableLiveData<String>().apply { value = "" }


    fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                // TODO click login
            }

            R.id.btn_sign_up -> {
                _signUpStatus.value = true
            }
        }
    }
}