package com.example.payment_qrcode.data.repository.impl

import com.example.payment_qrcode.data.local.PrefHelper
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.data.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val prefHelper: PrefHelper) : UserRepository {
    /**
     * Save status when user first open app
     */
    override fun saveStatusUseOpenApp(isFirst: Boolean) = prefHelper.saveStatusUseOpenApp(isFirst)

    /**
     * Get status user first open app
     * if status == false return false and save status = true
     * @return false if user first open app
     */
    override fun isStatusUseOpenApp(): Boolean = prefHelper.isStatusUseOpenApp()

    /**
     * Save List user register in Local
     */
    override fun saveListUserRegister(user: MutableList<User>) =
        prefHelper.saveListUserRegister(user)

    /**
     * Get List user register in Local
     */
    override fun getListUserRegister(): MutableList<User>? = prefHelper.getListUserRegister()

    /**
     * Save User in Local
     * @return true if save success, false if User is exists in Local or save fail
     */
    override fun saveUserRegister(user: User): Boolean = prefHelper.saveUserRegister(user)

    /**
     * Check User in Local
     * @return true if user exists
     */
    override fun checkUserRegister(user: User): Boolean = prefHelper.checkUserRegister(user)

    /**
     * Save the user information is currently logged in
     */
    override fun saveUserInfo(user: User) = prefHelper.saveUserInfo(user)

    /**
     * Get the user information is currently logged in
     */
    override fun getUserInfo(): User? = prefHelper.getUserInfo()

}