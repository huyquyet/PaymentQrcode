package com.example.payment_qrcode.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.payment_qrcode.data.repository.Currency
import kotlin.random.Random

fun Double.formatPrice(): String {
    return "%,.2f".format(this)
}

fun randomNumberString(range: Int): String {
    return Random.nextDouble(0.0, range.toDouble()).formatPrice()
}

fun randomNumberDouble(range: Int): Double {
    return Random.nextDouble(0.0, range.toDouble())
}

fun randomNumberDouble1(range: Int): Double {
    return Random.nextDouble(-range.toDouble(), range.toDouble())
}

fun AppCompatActivity.hideKeyboard() {
    val imm: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = this.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}

fun RecyclerView.setUpRecyclerViewVertical() {
    val linearLayoutManager =
        LinearLayoutManager(this.context, RecyclerView.VERTICAL, false).apply {
            isSmoothScrollbarEnabled = true
        }
    this.apply {
        setHasFixedSize(true)
        layoutManager = linearLayoutManager
    }
}

fun convertCurrency(@Currency fromCurrency: String?, @Currency toCurrency: String?): Float {
    val from = if (fromCurrency.isNullOrEmpty()) Currency.USD else fromCurrency
    val to = if (toCurrency.isNullOrEmpty()) Currency.USD else toCurrency
    // USD -> 23000, EUR -> 25000, GBP-> 28000
    return when (from) {
        Currency.USD -> convertCurrencyUSD(to)
        Currency.EUR -> convertCurrencyEUR(to)
        Currency.GBP -> convertCurrencyGBP(to)
        else -> convertCurrencyUSD(to)
    }
}

fun convertCurrencyUSD(@Currency toCurrency: String): Float {
    return when (toCurrency) {
        Currency.USD -> 1.0f
        Currency.EUR -> 0.92f
        Currency.GBP -> 0.82f
        else -> 1.0f
    }
}


fun convertCurrencyEUR(@Currency toCurrency: String): Float {
    return when (toCurrency) {
        Currency.USD -> 1.09f
        Currency.EUR -> 1.0f
        Currency.GBP -> 0.89f
        else -> 1.0f
    }
}


fun convertCurrencyGBP(@Currency toCurrency: String): Float {
    return when (toCurrency) {
        Currency.USD -> 1.22f
        Currency.EUR -> 1.12f
        Currency.GBP -> 1.0f
        else -> 1.0f
    }
}