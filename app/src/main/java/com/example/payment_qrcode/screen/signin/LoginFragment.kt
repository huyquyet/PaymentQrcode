package com.example.payment_qrcode.screen.signin

import androidx.fragment.app.viewModels
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
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
    }


    companion object {
        fun newInstance() = LoginFragment()
    }
}