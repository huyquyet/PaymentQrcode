package com.example.payment_qrcode.ui.screen.payment

import android.os.Handler
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.model.Balance
import com.example.payment_qrcode.data.model.Payment
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.utils.SingleLiveEvent
import com.example.payment_qrcode.utils.convertCurrency
import com.example.payment_qrcode.utils.formatPrice
import java.util.*
import javax.inject.Inject

class PaymentViewModel @Inject constructor(private val userRepository: UserRepository) :
    BaseViewModel() {

    private val _onBackClick = SingleLiveEvent<Boolean>().apply { value = false }
    val onBackClick: LiveData<Boolean>
        get() = _onBackClick

    /**
     * transaction == false if Payment method error or data Payment null
     */
    private val _transaction = SingleLiveEvent<Boolean>()
    val transaction: LiveData<Boolean>
        get() = _transaction

    var lvdName = MutableLiveData<String>().apply { value = "" }
    var lvdNameSupplier = MutableLiveData<String>().apply { value = "" }
    var lvdTotal = MutableLiveData<String>().apply { value = "" }
    var lvdSubTotal = MutableLiveData<String>().apply { value = "" }
    var lvdFee = MutableLiveData<String>().apply { value = "" }
    var lvdPaymentMethod = MutableLiveData<String>().apply { value = "" }
    var lvdPaymentAvailable = MutableLiveData<String>().apply { value = "" }
    var lvdButton = MutableLiveData<String>().apply { value = "" }
    var lvdEnableButton = MutableLiveData<Boolean>().apply { value = false }
    var lvdImageSupplier = MutableLiveData<Int>()

    private var payment: Payment? = null
    private var currentPayment: Double = 0.00

    fun onClick(v: View) {
        when (v.id) {
            R.id.img_back -> _onBackClick.value = true
            R.id.btn_pay -> {
                conductTransaction()
            }
        }
    }

    fun setData(payment: Payment?) {
        val balance = getBalance(userRepository)
        val paymentAvailable = balance?.balance ?: 0.00
        payment?.also { pay ->
            this.payment = pay
            lvdName.value = pay.name
            lvdTotal.value = "$ ${pay.total?.formatPrice()} ${pay.currency}"
            lvdSubTotal.value = "$${pay.subTotal?.formatPrice()}  ${pay.currency}"
            lvdFee.value = "$${pay.fee?.formatPrice()}  ${pay.currency}"
            lvdPaymentMethod.value = pay.paymentMethod?.name
            lvdPaymentAvailable.value = "$ ${paymentAvailable.formatPrice()}"

            lvdNameSupplier.value = pay.supplier?.name
            lvdImageSupplier.value = getImage(pay.supplier?.image)

            lvdButton.value = "Pay $${pay.total?.formatPrice()}"

            if (pay.total != null) {
                val currency = convertCurrency(pay.currency, balance?.currency ?: "")
                currentPayment = (if (pay.total == null) 0.00 else pay.total!!) * currency

                lvdButton.value = "Pay $${currentPayment.formatPrice()} ${balance?.currency?.toLowerCase(
                    Locale.ROOT)}"
                lvdEnableButton.value = paymentAvailable > currentPayment
            } else {
                lvdEnableButton.value = false
            }
        } ?: kotlin.run {
            _transaction.value = false
        }
    }

    private fun conductTransaction() {
        val balance = getBalance(userRepository)
        var paymentAvailable = balance?.balance ?: 0.00
        if (payment == null || paymentAvailable == 0.00) {
            _transaction.value = false
            return
        }

        _onLoading.value = true

        paymentAvailable -= currentPayment
        saveBalances(userRepository, paymentAvailable)
        Handler().postDelayed({
            _onLoading.value = false
            _transaction.value = true
        }, 2000)

    }

    private fun getImage(resource: Int?): Int {
        return resource?.let {
            when (resource) {
                1 -> R.drawable.image_1
                2 -> R.drawable.image_2
                3 -> R.drawable.image_3
                4 -> R.drawable.image_4
                else -> R.drawable.image_1
            }
        } ?: R.drawable.image_1
    }
}