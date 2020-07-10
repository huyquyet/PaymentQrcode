package com.example.payment_qrcode.data.local

import com.example.payment_qrcode.data.model.Balance
import com.example.payment_qrcode.data.model.User

interface PrefHelper {
    fun clearSharedPrefs()
    fun removeUserInfo()
    /**
     * Save status when user first open app
     */
    fun saveStatusUseOpenApp(isFirst: Boolean)

    /**
     * Get status user first open app
     * if status == false return false and save status = true
     * @return false if user first open app
     */
    fun isStatusUseOpenApp(): Boolean

    /**
     * Save List user register in Local
     */
    fun saveListUserRegister(user: MutableList<User>)

    /**
     * Get List user register in Local
     */
    fun getListUserRegister(): MutableList<User>?

    /**
     * Save the user information is currently logged in
     */
    fun saveUserInfo(user: User)

    /**
     * Get the user information is currently logged in
     */
    fun getUserInfo(): User?

    /**
     * Get List balance of list User register
     */
    fun getListBalances() : MutableList<Balance>?

    /**
     * Set List balance of list User register
     */
    fun setListBalances(balances : MutableList<Balance>)

}