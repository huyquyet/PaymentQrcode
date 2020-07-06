package com.example.payment_qrcode.ui.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}