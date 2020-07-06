package com.example.payment_qrcode.utils

import android.os.Handler
import com.example.payment_qrcode.data.Constants

class ThresholdClickTime {
    private var isBlockClick = false

    fun isBlockClick(): Boolean {
        return isBlockClick
    }

    fun setBlockClick(blockClick: Boolean) {
        isBlockClick = blockClick
        if (blockClick) {
            Handler().postDelayed({ isBlockClick = false }, Constants.THRESHOLD_CLICK_TIME.toLong())
        }
    }
}