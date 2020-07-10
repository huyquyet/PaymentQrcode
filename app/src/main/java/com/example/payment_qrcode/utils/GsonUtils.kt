package com.example.payment_qrcode.utils

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.math.BigDecimal

class GsonUtils {
    companion object {
        private var instance: Gson? = null

        fun getInstance(): Gson {
            return instance ?: kotlin.run {
                val gsonBuilder = GsonBuilder()
                gsonBuilder.registerTypeAdapter(
                    Double::class.java,
                    JsonSerializer { originalValue: Double?, _: Type?, _: JsonSerializationContext? ->
                        val bigValue = BigDecimal.valueOf(originalValue!!)
                        JsonPrimitive(bigValue.toPlainString())
                    }
                )
//                gsonBuilder.excludeFieldsWithoutExposeAnnotation()
                gsonBuilder.create()
            }.apply {
                instance = this
            }
        }

        fun objectToString(obj: Any?): String? {
            return getInstance().toJson(obj)
        }

        fun <T> stringToObject(json: String?, clazz: Class<T>?): T {
            return getInstance().fromJson(json, clazz)
        }

        inline fun <reified T> toListObject(
            json: String?
        ): MutableList<T> {
            val turnsType = object : TypeToken<MutableList<T>>() {}.type
            return getInstance().fromJson(json, turnsType)
        }

        fun <T> getType() : Type {
            return object : TypeToken<MutableList<T>>() {}.type
        }
    }

}