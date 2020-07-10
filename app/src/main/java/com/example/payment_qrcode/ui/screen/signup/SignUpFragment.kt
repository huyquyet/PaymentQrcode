package com.example.payment_qrcode.ui.screen.signup

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.databinding.FragmentSignUpBinding
import com.example.payment_qrcode.ui.screen.main.MainFragment
import com.example.payment_qrcode.ui.screen.signin.LoginFragment
import com.example.payment_qrcode.utils.replaceFragment
import com.example.payment_qrcode.utils.showToast

class SignUpFragment : BaseFragment<FragmentSignUpBinding, SignUpViewModel>() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    override val viewModel: SignUpViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_sign_up

    /**
     * Call in [onViewCreated] when view has created
     */
    override fun initData() {
        viewModel.setData()
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        with(viewModel) {
            switchLoginStatus.observe(viewLifecycleOwner, Observer { status ->
                if (status) {
                    gotoLogin()
                }
            })

            signUpStatus.observe(viewLifecycleOwner, Observer { user ->
                user?.run {
                    signSuccess(user)
                } ?: signUpFail()
            })
        }
    }

    private fun signUpFail() {
        showToast(R.string.fragment_sing_up_pls_check_email_password)
    }

    private fun signSuccess(user: User) {
        showToast("Sing up success. pls login!")
        replaceFragment(
            parentFragmentManager,
            LoginFragment.newInstance(),
            R.id.frame_main_activity,
            LoginFragment::class.java.name,
            addToBackStack = false,
            isTransaction = true
        )
    }

    private fun gotoLogin() {
        replaceFragment(
            parentFragmentManager,
            LoginFragment.newInstance(),
            R.id.frame_main_activity,
            LoginFragment::class.java.name,
            addToBackStack = false,
            isTransaction = true
        )
    }

}