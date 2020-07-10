package com.example.payment_qrcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.model.User
import javax.inject.Inject

class ShareViewModel @Inject constructor() : BaseViewModel() {
    private val _userInfo = MutableLiveData<User>()
    val userInfo: LiveData<User>
        get() = _userInfo

    private val _switchViewPager = MutableLiveData<Int>()
    val switchViewPager: LiveData<Int>
        get() = _switchViewPager


    fun setUserInfo(user: User) {
        // validate user
        if (user.email.isNullOrEmpty()) return
        _userInfo.value = user
    }


    fun switchViewPager(position: Int) {
        // validate position
        if (position in 0..2) {
            _switchViewPager.value = position
        }
    }
}