package com.example.payment_qrcode.ui.screen.main

import androidx.fragment.app.viewModels
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val viewModel: MainViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_main

    /**
     * Call in [onViewCreated] when view has created
     */
    override fun initData() {
        TODO("Not yet implemented")
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        TODO("Not yet implemented")
    }
}