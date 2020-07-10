package com.example.payment_qrcode.ui.screen.profile

import android.view.View
import androidx.lifecycle.LiveData
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.utils.SingleLiveEvent
import com.example.payment_qrcode.utils.isTrimNullOrEmpty
import javax.inject.Inject

class ProfileViewModel @Inject constructor(val userRepository: UserRepository) : BaseViewModel() {

    val lvsIconNotification = SingleLiveEvent<Int>().apply { value = R.drawable.icon_notification }
    val lvsIconInformation = SingleLiveEvent<Int>().apply { value = R.drawable.icon_user_data }
    val lvsIconSecurity = SingleLiveEvent<Int>().apply { value = R.drawable.icon_shield }
    val lvsIconHelp = SingleLiveEvent<Int>().apply { value = R.drawable.icon_help }
    val lvsIconTerms = SingleLiveEvent<Int>().apply { value = R.drawable.icon_legal }
    val lvdName = SingleLiveEvent<String>().apply { value = "Unknown" }

    private val _onItemClick = SingleLiveEvent<String>()
    val onItemClick: LiveData<String>
        get() = _onItemClick

    private val _onLogOut = SingleLiveEvent<StatusLogout>().apply { value = StatusLogout.NONE }
    val onLogOut: LiveData<StatusLogout>
        get() = _onLogOut


    fun onClick(v: View) {
        when (v.id) {
            R.id.profile_notification -> _onItemClick.value = "Notifications"
            R.id.profile_information -> _onItemClick.value = "Personal Information"
            R.id.profile_security_settings -> _onItemClick.value = "Security Settings"
            R.id.profile_help_support -> _onItemClick.value = "Help - Support"
            R.id.profile_terms_of_use -> _onItemClick.value = "Terms of Use"
            R.id.profile_log_out -> _onLogOut.value = StatusLogout.CONFIRM
        }
    }

    fun logout() {
        userRepository.removeUserInfo()
        _onLogOut.value = StatusLogout.LOGOUT
    }

    fun initData() {
        userRepository.getUserInfo()?.run {
            if (this.firstName == null && this.lastName == null) lvdName.value = "Unknown"
            else {
                when {
                    this.firstName == null -> {
                        lvdName.value = this.lastName
                    }
                    this.lastName == null -> {
                        lvdName.value = this.firstName
                    }
                    else -> {
                        // this.firstName != null && this.lastName != null
                        lvdName.value = this.firstName + " " + this.lastName
                    }
                }
            }
        }

    }
}

enum class StatusLogout {
    CONFIRM, LOGOUT, NONE
}