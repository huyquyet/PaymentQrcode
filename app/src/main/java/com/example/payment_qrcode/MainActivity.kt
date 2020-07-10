package com.example.payment_qrcode

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.payment_qrcode.ui.screen.splash.SplashFragment
import com.example.payment_qrcode.ui.widgets.LoadingDialog
import com.example.payment_qrcode.utils.ThresholdClickTime
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val shareViewModel by viewModels<ShareViewModel> { viewModelFactory }
    val thresholdClickTime: ThresholdClickTime = ThresholdClickTime()

    private var loadingDialog: LoadingDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splashFragment = SplashFragment.newInstance()
        addFragment(splashFragment, SplashFragment::class.java.simpleName)
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frame_main_activity, fragment, tag)
        }.commit()
    }

    fun showLoading(cancelable: Boolean) {
        if ((loadingDialog != null && loadingDialog!!.isAdded) || this.isFinishing) return
        loadingDialog = LoadingDialog().apply {
            isCancelable = cancelable
        }
        if (!this.isFinishing) {
            loadingDialog?.show(supportFragmentManager, "loading")
        }
    }

    fun showLoading() {
        showLoading(false)
    }

    fun hideLoading() {
        if (loadingDialog != null && loadingDialog?.isAdded == true && !this.isFinishing) {
            loadingDialog?.dismiss()
            loadingDialog = null
        }
    }

    fun userLogout() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val splashFragment = SplashFragment.newInstance()
        addFragment(splashFragment, SplashFragment::class.java.simpleName)
    }
}
