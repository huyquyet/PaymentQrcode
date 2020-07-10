package com.example.payment_qrcode.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import com.example.payment_qrcode.BR
import com.example.payment_qrcode.databinding.ItemProfileBinding

@BindingMethods(
    BindingMethod(
        type = CustomItemProfile::class,
        attribute = "item_profile_icon",
        method = "setIcon"
    ),
    BindingMethod(
        type = CustomItemProfile::class,
        attribute = "item_profile_name",
        method = "setName"
    )

)
class CustomItemProfile : LinearLayout {
    val obsIcon = ObservableInt()
    val obsName = ObservableField<String>()

    var binding: ItemProfileBinding? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        binding = ItemProfileBinding.inflate(LayoutInflater.from(context), this, true)
        binding?.setVariable(BR.viewModel, this)
    }

    fun setIcon(icon: Int) {
        obsIcon.set(icon)
    }

    fun setName(name: String) {
        obsName.set(name)
    }

}