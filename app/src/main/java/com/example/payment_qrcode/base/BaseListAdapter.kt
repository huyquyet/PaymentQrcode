package com.example.payment_qrcode.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import java.util.concurrent.Executors

abstract class BaseListAdapter<Item, ViewHolder : BaseViewHolder<Item>>(
    callBack: DiffUtil.ItemCallback<Item>
) : ListAdapter<Item, ViewHolder>(
    AsyncDifferConfig.Builder<Item>(callBack)
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {
    private var listData: ArrayList<Item>? = null

    protected abstract fun getViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        getViewHolder(parent, LayoutInflater.from(parent.context), viewType)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBindData(getItem(position))
    }

    override fun submitList(list: List<Item>?) {
        super.submitList(ArrayList<Item>(list ?: listOf()))
    }

    fun getData(): ArrayList<Item> {
        return listData ?: ArrayList<Item>().apply { listData = this }
    }

    fun setData(data: List<Item>?) {
        getData().clear()
        data?.let {
            getData().addAll(it)
        }
        submitList(getData())
    }

    fun addData(data: List<Item>?) {
        getData().addAll(data ?: ArrayList())
        submitList(ArrayList<Item>(getData()))
    }

    fun clearData() {
        getData().clear()
        submitList(getData())
    }

    fun refreshData() {
        submitList(getData())
    }
}