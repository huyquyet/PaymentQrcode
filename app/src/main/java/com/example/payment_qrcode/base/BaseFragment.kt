package com.example.payment_qrcode.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import com.example.payment_qrcode.BR
import com.example.payment_qrcode.MainActivity
import com.example.payment_qrcode.ShareViewModel
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerFragment() {
    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewDataBinding by autoCleared<T>()

    val shareViewModel by viewModels<ShareViewModel>({ activity as MainActivity }) { viewModelFactory }

    /**
     * Call in [onViewCreated] when view has created
     */
    abstract fun initData()

    /**
     * Call in [onViewCreated] after [initData]
     */
    abstract fun observeField()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }

        initData()
        observeField()
    }

    /**
     * Use when single click in multi views and lock multi click
     */
    @Synchronized
    fun canNotClick(): Boolean {
        (activity as MainActivity).thresholdClickTime.also { thresholdClickTime ->
            return if (thresholdClickTime.isBlockClick()) {
                true
            } else {
                thresholdClickTime.setBlockClick(true)
                false
            }
        }
    }
}