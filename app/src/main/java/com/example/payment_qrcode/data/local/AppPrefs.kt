package com.example.payment_qrcode.data.local

import com.example.payment_qrcode.data.model.User
import javax.inject.Inject

class AppPrefs @Inject constructor(private val sharedPrefsApi: SharedPrefsApi) : PrefHelper {
    /**
     * Save status when user first open app
     */
    override fun saveStatusUseOpenApp(isFirst: Boolean) =
        sharedPrefsApi.set(SharePrefsKey.IS_FIRST, isFirst)

    /**
     * Get status user first open app
     * if status == false return false and save status = true
     * @return false if user first open app
     */
    override fun isStatusUseOpenApp(): Boolean {
        val status = sharedPrefsApi.get(SharePrefsKey.IS_FIRST, false)
        if (!status) sharedPrefsApi.set(SharePrefsKey.IS_FIRST, true)
        return status
    }

    /**
     * Save List user register in Local
     */
    override fun saveListUserRegister(user: MutableList<User>) =
        sharedPrefsApi.setObject(SharePrefsKey.USER_INFO_LIST, user)

    /**
     * Get List user register in Local
     */
    override fun getListUserRegister(): MutableList<User>? =
        sharedPrefsApi.getObject<MutableList<User>>(SharePrefsKey.USER_INFO_LIST)

    /**
     * Save User in Local
     * @return true if save success, false if User is exists in Local
     */
    override fun saveUserRegister(user: User): Boolean {
        val userList = getListUserRegister()
        return userList?.let { users ->
            var userExists = false

            /* Check user has been exists in list */
            for (u in users) {
                if (u.id == user.id) {
                    userExists = true
                    break
                }
            }

            /* user not yet in list, save user in list */
            if (!userExists) {
                userList.add(user)
                sharedPrefsApi.setObject(SharePrefsKey.USER_INFO_LIST, userList)
            }
            !userExists
        } ?: ArrayList<User>().let { list ->
            list.add(user)
            sharedPrefsApi.setObject(SharePrefsKey.USER_INFO_LIST, list)
            true
        }
    }

    /**
     * Check User in Local
     * @return true if user exists
     */
    override fun checkUserRegister(user: User): Boolean {
        val userList = getListUserRegister()
        return userList?.let { users ->
            /* Check user has been exists in list */
            for (u in users) {
                if (u.id == user.id) {
                    return true
                }
            }
            return false
        } ?: false
    }

    /**
     * Save the user information is currently logged in
     */
    override fun saveUserInfo(user: User) = sharedPrefsApi.setObject(SharePrefsKey.USER_INFO, user)

    /**
     * Get the user information is currently logged in
     */
    override fun getUserInfo(): User? = sharedPrefsApi.getObject<User>(SharePrefsKey.USER_INFO)

}