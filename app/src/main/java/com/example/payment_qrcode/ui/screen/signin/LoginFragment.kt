package com.example.payment_qrcode.ui.screen.signin

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.databinding.FragmentLoginBinding
import com.example.payment_qrcode.ui.screen.main.MainFragment
import com.example.payment_qrcode.ui.screen.signup.SignUpFragment
import com.example.payment_qrcode.utils.replaceFragment
import com.example.payment_qrcode.utils.showToast


class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    override val viewModel: LoginViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_login

    /**
     * Call in [onViewCreated] when view has created
     */
    override fun initData() {
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        with(viewModel) {
            switchSignUpStatus.observe(viewLifecycleOwner, Observer { status ->
                if (status) {
                    gotoSignUp()
                }
            })

            loginStatus.observe(viewLifecycleOwner, Observer { user ->
                user?.let {
                    if (it.email.isNullOrEmpty()) {
                        loginFail()
                    } else {
                        loginSuccess(user)
                    }
                } ?: run {
                    loginFail()
                }
            })

            loginTouchId.observe(viewLifecycleOwner, Observer { status ->
                if (status) {
                    loginViaTouchId()
                }
            })
        }
    }

    private fun loginFail() {
        viewDataBinding.txtLoginError.text = getString(R.string.fragment_login_email_or_pass_wrong)
    }

    private fun loginSuccess(user: User) {
        shareViewModel.setUserInfo(user)
        gotoMain()
    }

    private fun loginViaTouchId() {
        showToast(R.string.fragment_login_via_touch_id)
    }

    private fun gotoMain() {
        replaceFragment(
            parentFragmentManager,
            MainFragment.newInstance(),
            R.id.frame_main_activity,
            MainFragment::class.java.name,
            addToBackStack = false,
            isTransaction = true
        )
    }

    private fun gotoSignUp() {
        replaceFragment(
            parentFragmentManager,
            SignUpFragment.newInstance(),
            R.id.frame_main_activity,
            LoginFragment::class.java.name,
            addToBackStack = false,
            isTransaction = true
        )
    }

}