package com.example.payment_qrcode

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.payment_qrcode.screen.splash.SplashFragment
import com.example.payment_qrcode.utils.ThresholdClickTime
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel by viewModels<ShareViewModel> { viewModelFactory }
    val thresholdClickTime: ThresholdClickTime = ThresholdClickTime()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splashFragment = SplashFragment.newInstance()
        addFragment(splashFragment, SplashFragment::class.java.simpleName)
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.frame_main_activity, fragment, tag)
            addToBackStack(tag)
        }.commit()
    }
}
