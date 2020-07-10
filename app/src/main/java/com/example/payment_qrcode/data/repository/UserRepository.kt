package com.example.payment_qrcode.data.repository

import com.example.payment_qrcode.data.model.Balance
import com.example.payment_qrcode.data.model.User

interface UserRepository : Repository {

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
     * Save User in Local
     * @return true if save success, false if User is exists in Local or save fail
     */
    fun saveUserRegister(user: User): Boolean

    /**
     * Check User in Local
     * @return true if user exists
     */
    fun checkUserRegister(email: String?, password : String?): Boolean

    /**
     * Get User in Local
     * @return user if user exists
     */
    fun getUserLogin(email: String?, password : String?): User?

    /**
     * Save the user information is currently logged in
     */
    fun saveUserInfo(user: User)

    /**
     * Get the user information is currently logged in
     */
    fun getUserInfo(): User?

    /**
     * Remove data UserInfo
     */
    fun removeUserInfo()

    /**
     * Get List balance of list User register
     */
    fun getListBalances() : MutableList<Balance>?

    /**
     * Set List balance of list User register
     */
    fun setListBalances(balances : MutableList<Balance>)

}