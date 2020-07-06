package com.example.payment_qrcode.ui.screen.splash

import android.os.Handler
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.databinding.FragmentSplashBinding
import com.example.payment_qrcode.ui.screen.main.MainFragment
import com.example.payment_qrcode.ui.screen.signin.LoginFragment
import com.example.payment_qrcode.utils.replaceFragment

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    override val viewModel: SplashViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_splash

    /**
     * Call in [onViewCreated]
     */
    override fun initData() {
        viewModel.checkLogin()
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        with(viewModel) {
            loginStatus.observe(viewLifecycleOwner, Observer { status ->
                Handler().postDelayed({
                    if (status) {
                        gotoHome()
                    } else {
                        gotoLogin()
                    }
                }, 2000)
            })
        }
    }

    private fun gotoLogin() {
        replaceFragment(
            parentFragmentManager,
            LoginFragment.newInstance(),
            R.id.frame_main_activity,
            LoginFragment::class.java.name,
            addToBackStack = false
        )
    }

    private fun gotoHome() {
        replaceFragment(
            parentFragmentManager,
            MainFragment.newInstance(),
            R.id.frame_main_activity,
            MainFragment::class.java.name,
            addToBackStack = false
        )
    }
}