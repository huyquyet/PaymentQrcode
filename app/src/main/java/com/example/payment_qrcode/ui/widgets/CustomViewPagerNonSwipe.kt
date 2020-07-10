package com.example.payment_qrcode.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPagerNonSwipe : ViewPager {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)


    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // Check allow swiping to switch between pages
//        if (this.swipeEnabled) {
//            try {
//                return super.onInterceptTouchEvent(event)
//            } catch (e: IllegalArgumentException) {
//            }
//        }
        return false
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        // Check allow swiping to switch between pages
//        if (this.swipeEnabled) {
//            try {
//                return super.onTouchEvent(event)
//            } catch (ex: IllegalArgumentException) {
//            }
//        }
        return false
    }
}