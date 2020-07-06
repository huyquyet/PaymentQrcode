package com.example.payment_qrcode.screen.wallet

import androidx.fragment.app.viewModels
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.databinding.FragmentWalletBinding
import com.example.payment_qrcode.R

class WalletFragment : BaseFragment<FragmentWalletBinding, WalletViewModel>() {
    override val viewModel: WalletViewModel by viewModels { viewModelFactory }

    override val layoutId: Int = R.layout.fragment_wallet

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


}