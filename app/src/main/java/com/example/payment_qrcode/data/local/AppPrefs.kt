package com.example.payment_qrcode.data.local

import com.example.payment_qrcode.data.model.Balance
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.utils.GsonUtils
import com.example.payment_qrcode.utils.formatPrice
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AppPrefs @Inject constructor(private val sharedPrefsApi: SharedPrefsApi) : PrefHelper {
    override fun clearSharedPrefs() {
        sharedPrefsApi.clear()
    }

    override fun removeUserInfo() {
        sharedPrefsApi.remove(SharePrefsKey.USER_INFO)
    }

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
    override fun getListUserRegister(): MutableList<User>? {
        val data = sharedPrefsApi.get(SharePrefsKey.USER_INFO_LIST, "")
        val type = object : TypeToken<MutableList<User>>() {}.type
        return GsonUtils.getInstance().fromJson<MutableList<User>>(data, type)
    }

    /**
     * Save the user information is currently logged in
     */
    override fun saveUserInfo(user: User) =
        sharedPrefsApi.setObject<User>(SharePrefsKey.USER_INFO, user)

    /**
     * Get the user information is currently logged in
     */
    override fun getUserInfo(): User? = sharedPrefsApi.getObject<User>(SharePrefsKey.USER_INFO)

    /**
     * Get List balance of list User register
     */
    override fun getListBalances(): MutableList<Balance>? {
        val data = sharedPrefsApi.get(SharePrefsKey.BALANCE_LIST, "")
        val type = object : TypeToken<MutableList<Balance>>() {}.type
        return GsonUtils.getInstance().fromJson<MutableList<Balance>>(data, type)
    }

    /**
     * Set List balance of list User register
     */
    override fun setListBalances(balances: MutableList<Balance>) {
        sharedPrefsApi.setObject(SharePrefsKey.BALANCE_LIST, balances)
    }

}