package com.example.payment_qrcode.utils

import com.google.gson.*
import java.lang.reflect.Type
import java.math.BigDecimal
import java.util.*

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

        fun <T> toListObject(
            json: String?,
            clazz: Class<Array<T>?>?
        ): List<T> {
            val t = getInstance().fromJson(json, clazz)!!
            return ArrayList(listOf(*t))
        }
    }

}