package com.example.payment_qrcode.data.repository.impl

import com.example.payment_qrcode.data.local.PrefHelper
import com.example.payment_qrcode.data.model.Balance
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
    override fun saveUserRegister(user: User): Boolean {
        val userList = getListUserRegister()
        return userList?.let { users ->
            var userExists = false

            /* Check user has been exists in list */
            for (u in users) {
                if (u.email == user.email && u.password == user.password) {
                    userExists = true
                    break
                }
            }

            /* user not yet in list, save user in list */
            if (!userExists) {
                userList.add(user)
                prefHelper.saveListUserRegister(userList)
            }
            !userExists
        } ?: ArrayList<User>().let { list ->
            list.add(user)
            prefHelper.saveListUserRegister(list)
            true
        }
    }

    /**
     * Check User in Local
     * @return true if user exists
     */
    override fun checkUserRegister(email: String?, password: String?): Boolean {
        if (password.isNullOrEmpty() || password.isNullOrEmpty()) return false
        val userList: MutableList<User>? = getListUserRegister()
        return userList?.let { users ->
            /* Check user has been exists in list */
            for (u in users) {
                if (u.email == email) {
                    return true
                }
            }
            false
        } ?: false
    }

    /**
     * Get User in Local
     * @return user if user exists
     */
    override fun getUserLogin(email: String?, password: String?): User? {
        if (password.isNullOrEmpty() || password.isNullOrEmpty()) return null
        val userList: MutableList<User>? = getListUserRegister()
        return userList?.let { users ->
            /* Check user has been exists in list */
            for (u in users) {
                if (u.email == email && u.password == password) {
                    return u
                }
            }
            return null
        }
    }

    /**
     * Save the user information is currently logged in
     */
    override fun saveUserInfo(user: User) = prefHelper.saveUserInfo(user)

    /**
     * Get the user information is currently logged in
     */
    override fun getUserInfo(): User? = prefHelper.getUserInfo()

    override fun clearSharedPrefs() {
        prefHelper.clearSharedPrefs()
    }

    override fun removeUserInfo() {
        prefHelper.removeUserInfo()
    }

    /**
     * Get List balance of list User register
     */
    override fun getListBalances(): MutableList<Balance>? {
        return prefHelper.getListBalances()
    }

    /**
     * Set List balance of list User register
     */
    override fun setListBalances(balances: MutableList<Balance>) {
        prefHelper.setListBalances(balances)
    }

}