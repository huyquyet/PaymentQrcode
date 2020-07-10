package com.example.payment_qrcode.ui.screen.wallet

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.example.payment_qrcode.base.BaseListAdapter
import com.example.payment_qrcode.base.BaseViewHolder
import com.example.payment_qrcode.data.model.ItemWallet
import com.example.payment_qrcode.data.model.TypeItemWallet
import com.example.payment_qrcode.databinding.ItemWalletBinding
import com.example.payment_qrcode.databinding.ItemWalletDateBinding
import com.example.payment_qrcode.listeners.OnItemCLick
import com.example.payment_qrcode.utils.SingleLiveEvent
import com.example.payment_qrcode.utils.formatPrice

val diffItemWallet = object : DiffUtil.ItemCallback<ItemWallet>() {
    override fun areItemsTheSame(oldItem: ItemWallet, newItem: ItemWallet): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ItemWallet, newItem: ItemWallet): Boolean =
        oldItem == newItem
}

class RecyclerWalletAdapter :
    BaseListAdapter<ItemWallet, BaseViewHolderWallet>(diffItemWallet) {

    var onItemCLick: OnItemCLick<ItemWallet>? = null

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }

    override fun getViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): BaseViewHolderWallet {
        return if (viewType == TypeItemWallet.ITEM) {
            ItemWalletHolder(ItemWalletBinding.inflate(inflater, parent, false))
        } else {
            ItemWalletDateHolder(ItemWalletDateBinding.inflate(inflater, parent, false))
        }
    }

    inner class ItemWalletHolder(val binding: ItemWalletBinding) :
        BaseViewHolderWallet(binding) {

        override fun clickMore() {
            onItemCLick?.onItemClick(item, adapterPosition)
        }

        override fun bindData(item: ItemWallet) {
            lvdImageResource.value = item.imageUrl
            lvdWalletName.value = item.name
            lvdWalletDescription.value = item.description
            lvdWalletPrice.value = item.price?.let { price ->
                if (price > 0) {
                    lvdWalletReason.value = ""
                    colorPrice.value = Color.GREEN
                    "$ ${price.formatPrice()}"
                } else {
                    lvdWalletReason.value = item.reason
                    if (item.reason.isNullOrEmpty()) {
                        colorPrice.value = Color.BLACK
                    } else {
                        colorPrice.value = Color.RED
                    }
                    "-$ ${(-price).formatPrice()}"
                }
            } ?: run {
                lvdWalletReason.value = ""
                "$ 0.00"
            }
        }
    }

    inner class ItemWalletDateHolder(binding: ItemWalletDateBinding) :
        BaseViewHolderWallet(binding) {

        override fun bindData(item: ItemWallet) {
            lvdDateTime.value = item.date
        }
    }
}

abstract class BaseViewHolderWallet(binding: ViewDataBinding) :
    BaseViewHolder<ItemWallet>(binding) {
    val lvdImageResource = SingleLiveEvent<Int>()
    val lvdWalletName = SingleLiveEvent<String>()
    val lvdWalletDescription = SingleLiveEvent<String>()
    val lvdWalletPrice = SingleLiveEvent<String>()
    val lvdWalletReason = SingleLiveEvent<String>()
    val lvdDateTime = SingleLiveEvent<String>()
    val colorPrice = SingleLiveEvent<Int>()

    open fun clickMore() {
    }
}

