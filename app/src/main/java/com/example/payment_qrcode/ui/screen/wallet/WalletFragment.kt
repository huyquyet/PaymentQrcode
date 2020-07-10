package com.example.payment_qrcode.ui.screen.wallet

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.databinding.FragmentWalletBinding
import com.example.payment_qrcode.R
import com.example.payment_qrcode.data.model.ItemWallet
import com.example.payment_qrcode.listeners.EndlessRecyclerOnScrollListener
import com.example.payment_qrcode.listeners.OnItemCLick
import com.example.payment_qrcode.utils.setUpRecyclerViewVertical
import com.example.payment_qrcode.utils.showToast

class WalletFragment : BaseFragment<FragmentWalletBinding, WalletViewModel>() {

    companion object {
        fun newInstance() = WalletFragment()
    }

    override val viewModel: WalletViewModel by viewModels { viewModelFactory }

    override val layoutId: Int = R.layout.fragment_wallet
    private val endlessRecyclerOnScrollListener = object : EndlessRecyclerOnScrollListener() {
        override fun onLoadMore() {
            if (recyclerWalletAdapter.itemCount < 50) {
                viewModel.loadMore()
            }
        }
    }
    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        viewModel.refreshData()
        viewDataBinding.swipeRefreshWallet.isRefreshing = false
    }

    private val recyclerWalletAdapter = RecyclerWalletAdapter().apply {
        onItemCLick = object : OnItemCLick<ItemWallet> {
            override fun onItemClick(item: ItemWallet?, position: Int) {
                showToast(item?.name)
            }
        }
    }

    /**
     * Call in [onViewCreated] when view has created
     */
    override fun initData() {
        viewDataBinding.apply {
            swipeRefreshWallet.setOnRefreshListener(onRefreshListener)
            with(recyclerWallet) {
                setUpRecyclerViewVertical()
                addOnScrollListener(endlessRecyclerOnScrollListener)
                adapter = recyclerWalletAdapter
            }
        }
        
        viewModel.loadPaymentAvailable()
        viewModel.loadData()
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        with(viewModel) {
            dataRecycler.observe(viewLifecycleOwner, Observer { data ->
                if (data.size < 20) {
                    recyclerWalletAdapter.addData(data)
                } else {
                    recyclerWalletAdapter.setData(data)
                }
            })

            onClickVault.observe(viewLifecycleOwner, Observer { status ->
                if (status) {
                    showToast("Click onClickVault")
                }
            })

            onClickTopUp.observe(viewLifecycleOwner, Observer { status ->
                if (status) {
                    showToast("Click onClickTopUp")
                }
            })
        }
    }


}