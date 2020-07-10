package com.example.payment_qrcode.ui.screen.payment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.MainActivity
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.data.model.Payment
import com.example.payment_qrcode.data.model.PaymentSuccess
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.databinding.FragmentPaymentBinding
import com.example.payment_qrcode.listeners.PaymentSuccessCallback
import com.example.payment_qrcode.utils.showToast


class PaymentFragment : BaseFragment<FragmentPaymentBinding, PaymentViewModel>(),
    PaymentSuccessCallback {

    companion object {
        fun newInstance(payment: Payment) = PaymentFragment().apply {
            arguments = Bundle().apply {
                this.putParcelable(ARG_PAYMENT, payment)
            }
        }

        private const val ARG_PAYMENT = "ARG_PAYMENT"
    }

    override val viewModel: PaymentViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_payment

    var payment: Payment? = null
    var paymentSuccessCallback: PaymentSuccessCallback? = null
    var paymentSuccess: Boolean = false

    override fun beforeAddContent() {
        super.beforeAddContent()
        payment = arguments?.getParcelable(ARG_PAYMENT)
    }

    /**
     * Call in [onViewCreated] when view has created
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun initData() {
        with(viewModel) {
            setData(payment)
        }
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        with(viewModel) {
            onBackClick.observe(viewLifecycleOwner, Observer { onBack ->
                if (onBack) (activity as MainActivity).onBackPressed()
            })

            transaction.observe(viewLifecycleOwner, Observer { status ->
                if (status) transactionSuccess() else transactionFail()
            })
        }
    }

    private fun transactionSuccess() {
        val paymentSuccess =
            PaymentSuccess(id = 1, transactionCode = "123456FGHE9392991", transactionStatus = true)
        val dialog = DialogPaymentSuccess.newInstance(paymentSuccess)
            .apply {
                paymentSuccessCallback = this@PaymentFragment
            }

        dialog.show(
            (activity as MainActivity).supportFragmentManager,
            DialogPaymentSuccess::class.java.simpleName
        )

    }

    private fun transactionFail() {
        showToast(R.string.fragment_payment_transaction_fail)
    }

    override fun paymentCallback(status: Boolean) {
        if (status) {
            paymentSuccess = true
            (activity as MainActivity).onBackPressed()
        }
    }

    override fun onDestroy() {
        paymentSuccessCallback?.paymentCallback(paymentSuccess)
        super.onDestroy()
    }
}