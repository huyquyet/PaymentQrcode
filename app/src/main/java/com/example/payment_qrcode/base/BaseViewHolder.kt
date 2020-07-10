package com.example.payment_qrcode.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.payment_qrcode.BR

abstract class BaseViewHolder<Item>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var item: Item? = null

    fun onBindData(item: Item) {
        binding.setVariable(BR.viewModel, this)
        this.item = item
        bindData(item)
        binding.executePendingBindings()
    }

    abstract fun bindData(item: Item)

}