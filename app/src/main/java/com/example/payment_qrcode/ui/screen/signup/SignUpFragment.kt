package com.example.payment_qrcode.ui.screen.signup

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.databinding.FragmentSignUpBinding

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
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        with(viewModel) {
            signUpStatus.observe(viewLifecycleOwner, Observer { status ->
                gotoSignUp()
            })
        }
    }

    private fun gotoSignUp() {
//        replaceFragment(
//            parentFragmentManager,
//            SignUpFragment.newInstance(),
//            R.id.frame_main_activity,
//            SignUpFragment::class.java.name,
//            addToBackStack = false
//        )
    }

}