package com.example.payment_qrcode.ui.screen.scan

import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.data.model.Payment
import com.example.payment_qrcode.databinding.FragmentScanBinding
import com.example.payment_qrcode.listeners.PaymentSuccessCallback
import com.example.payment_qrcode.ui.screen.payment.PaymentFragment
import com.example.payment_qrcode.utils.GsonUtils
import com.example.payment_qrcode.utils.replaceFragment
import com.example.payment_qrcode.utils.showToast
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView


class ScanFragment : BaseFragment<FragmentScanBinding, ScanViewModel>(),
    ZXingScannerView.ResultHandler, PaymentSuccessCallback {
    private val requestCodeCamera = 101

    companion object {
        fun newInstance() = ScanFragment()
    }

    override val viewModel: ScanViewModel by viewModels { viewModelFactory }

    override val layoutId: Int = R.layout.fragment_scan

    /**
     * Call in [onViewCreated] when view has created
     */
    override fun initData() {
        viewDataBinding.frameCamera.setResultHandler(this)
    }

    override fun onResume() {
        super.onResume()
        Log.d("-------------: ", "ScanFragment onResume: ")
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), requestCodeCamera)
        } else {
            viewDataBinding.frameCamera.startCamera()
        }
    }

    override fun onPause() {
        super.onPause()
        viewDataBinding.frameCamera.stopCamera()
    }

    override fun onStop() {
        viewDataBinding.frameCamera.stopCamera()
        super.onStop()
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
    }

    override fun handleResult(p0: Result?) {
        p0?.run {
            if (!this.text.isNullOrEmpty()) {
                val text = this.text!!
                var payment: Payment? = null
                try {
                    payment = GsonUtils.stringToObject(text, Payment::class.java)
                } catch (e: Exception) {
                    showToast("Scan error!")
                    viewDataBinding.frameCamera.resumeCameraPreview(this@ScanFragment)
                }

                if (payment == null) return
                showLoading()
                Handler().postDelayed(
                    {
                        hideLoading()
                        openPayment(payment)
                    }, 1000
                )
            }
        }
    }

    private fun openPayment(payment: Payment) {
        val paymentFragment = PaymentFragment.newInstance(payment).apply {
            paymentSuccessCallback = this@ScanFragment
        }
        activity?.let {
            replaceFragment(
                it.supportFragmentManager,
                paymentFragment,
                R.id.frame_main_activity,
                PaymentFragment::class.java.name,
                addToBackStack = true,
                isTransaction = true
            )
        }
    }

    override fun paymentCallback(status: Boolean) {
        if (status) {
            shareViewModel.switchViewPager(0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == this.requestCodeCamera && grantResults.isNotEmpty()
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            viewDataBinding.frameCamera.startCamera()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}