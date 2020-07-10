package com.example.payment_qrcode.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.payment_qrcode.data.model.Balance
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.utils.SingleLiveEvent

open class BaseViewModel() : ViewModel() {
    protected val _onLoading = SingleLiveEvent<Boolean>().apply { value = false }
    val onLoading: LiveData<Boolean>
        get() = _onLoading

    fun saveBalances(userRepository: UserRepository, balance: Double) {
        val balances = userRepository.getListBalances()
        val user = userRepository.getUserInfo()
        val ba = getBalance(userRepository)

        if (ba == null) {
            if (balances == null) {
                ArrayList<Balance>().apply {
                    add(Balance(user!!.email!!, balance))
                    userRepository.setListBalances(this)
                }
            } else {
                balances.add(Balance(user!!.email!!, balance))
                userRepository.setListBalances(balances)
            }
        } else {
            // ba != null
            if (balances == null) {
                ArrayList<Balance>().apply {
                    ba.balance = balance
                    add(ba)
                    userRepository.setListBalances(this)
                }
            } else {
                for (b in balances) {
                    if (b.email == user!!.email) {
                        b.balance = balance
                        break
                    }
                }
                userRepository.setListBalances(balances)
            }
        }
    }

    fun getBalance(userRepository: UserRepository): Balance? {
        val balances = userRepository.getListBalances()
        val user = userRepository.getUserInfo()
        if (user == null || balances == null) return null
        else {
            for (balance in balances) {
                if (balance.email == user.email) {
                    return balance
                }
            }
            return null
        }
    }
}