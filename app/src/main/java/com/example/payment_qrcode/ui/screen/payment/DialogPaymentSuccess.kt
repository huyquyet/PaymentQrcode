package com.example.payment_qrcode.ui.screen.payment

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.example.payment_qrcode.BR
import com.example.payment_qrcode.data.model.PaymentSuccess
import com.example.payment_qrcode.databinding.FragmentDialogPaymentSuccessBinding
import com.example.payment_qrcode.listeners.PaymentSuccessCallback
import com.example.payment_qrcode.utils.SingleLiveEvent

class DialogPaymentSuccess : DialogFragment() {
    companion object {
        fun newInstance(payment: PaymentSuccess) = DialogPaymentSuccess().apply {
            arguments = Bundle().apply {
                this.putParcelable(ARG_PAYMENT, payment)
            }
        }

        private const val ARG_PAYMENT = "ARG_PAYMENT"
    }

    var binding: FragmentDialogPaymentSuccessBinding? = null
    var payment: PaymentSuccess? = null
    var paymentSuccessCallback: PaymentSuccessCallback? = null

    val lvdTransactionCode = SingleLiveEvent<String>()

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null && dialog.window != null) {
            dialog.window!!.apply {
                setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT

                )
                setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (dialog.window != null) {
            dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return dialog
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        payment = arguments?.getParcelable(ARG_PAYMENT)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDialogPaymentSuccessBinding.inflate(inflater, container, false);
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.setVariable(BR.viewModel, this)

        lvdTransactionCode.value = payment?.transactionCode ?: "error!"
    }

    fun onClick() {
        paymentSuccessCallback?.paymentCallback(true)
        dismiss()
    }
}