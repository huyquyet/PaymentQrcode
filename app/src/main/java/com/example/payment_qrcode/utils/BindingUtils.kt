package com.example.payment_qrcode.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.example.payment_qrcode.R
import com.example.payment_qrcode.data.Constants
import com.example.payment_qrcode.listeners.OnDrawableClickListener
import com.example.payment_qrcode.ui.widgets.CustomEditText
import com.example.payment_qrcode.ui.widgets.CustomTextView
import java.io.File

@BindingAdapter("onDrawableClick")
fun CustomEditText.setOnDrawableClick(
    listener: OnDrawableClickListener?
) {
    this.clickListener = listener
}

fun CustomTextView.bindHtml(text: String?) {
}


@BindingAdapter("clickSafe")
fun View.setClickSafe(listener: View.OnClickListener?) {
    setOnClickListener(object : View.OnClickListener {
        var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < Constants.THRESHOLD_CLICK_TIME) {
                return
            }
            listener?.onClick(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

@SuppressLint("CheckResult")
@BindingAdapter(
    value = ["loadImage", "centerCrop", "fitCenter", "circleCrop", "roundedCorner"],
    requireAll = false
)
fun ImageView.loadImages(
    resource: Int,
    centerCrop: Boolean = false, fitCenter: Boolean = false, circleCrop: Boolean = false,
    roundedCorner: Boolean = false
) {

    val requestOptions = RequestOptions().apply {
        if (centerCrop) this.centerCrop()
        if (fitCenter) this.fitCenter()
        if (circleCrop) this.circleCrop()
        if (roundedCorner) this.transforms(
            CenterCrop(),
            RoundedCorners(this@loadImages.resources.getDimension(R.dimen.dp_10).toInt())
        )
    }

    Glide.with(context).load(resource).apply(requestOptions).into(this)
}

@BindingAdapter("textColor")
fun TextView.setColor(@ColorInt color : Int) {
    this.setTextColor(color)
}