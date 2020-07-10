package com.example.payment_qrcode.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.utils.GsonUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList
import javax.inject.Inject

class SharedPrefsApi @Inject constructor(context: Context) {

    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    fun set(key: String, value: String) =
        sharedPreferences.edit().apply { putString(key, value) }.apply()

    fun get(key: String, defValue: String): String =
        sharedPreferences.getString(key, defValue) ?: ""

    fun set(key: String, value: Int) = sharedPreferences.edit().apply { putInt(key, value) }.apply()

    fun get(key: String, defValue: Int) = sharedPreferences.getInt(key, defValue)

    fun set(key: String, value: Boolean) =
        sharedPreferences.edit().apply { putBoolean(key, value) }.apply()

    fun get(key: String, defValue: Boolean) = sharedPreferences.getBoolean(key, defValue)

    fun set(key: String, value: Long) =
        sharedPreferences.edit().apply { putLong(key, value) }.apply()

    fun get(key: String, defValue: Long) = sharedPreferences.getLong(key, defValue)

    fun clear() = sharedPreferences.edit().apply { clear() }.apply()

    fun remove(key: String) = sharedPreferences.edit().apply { remove(key) }.apply()

    fun contains(key: String) = sharedPreferences.contains(key)

    inline fun <reified T> setObject(key: String, value: T) = sharedPreferences.edit().apply {
        putString(key, GsonUtils.objectToString(value))
    }.apply()

    inline fun <reified T> getObject(key: String): T? = run {
        val data = get(key, "")
        return if (data.isEmpty()) {
            null
        } else {
            GsonUtils.stringToObject(data, T::class.java)
        }
    }

    inline fun <reified T> getListObject(key: String): MutableList<T>? = run {
        val data = get(key, "")
        return if (data.isEmpty()) {
            null
        } else {
            GsonUtils.toListObject(data)
        }
    }
}