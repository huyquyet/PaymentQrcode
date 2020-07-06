package com.example.payment_qrcode.ui.screen.signin

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.databinding.FragmentLoginBinding
import com.example.payment_qrcode.ui.screen.signup.SignUpFragment
import com.example.payment_qrcode.utils.replaceFragment

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
            signUpStatus.observe(viewLifecycleOwner, Observer { status ->
                if (status) {
                    gotoSignUp()
                }
            })
        }
    }

    private fun gotoSignUp() {
        replaceFragment(
            parentFragmentManager,
            SignUpFragment.newInstance(),
            R.id.frame_main_activity,
            LoginFragment::class.java.name
        )
    }

}