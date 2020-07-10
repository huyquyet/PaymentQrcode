package com.example.payment_qrcode.ui.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import com.example.payment_qrcode.listeners.OnDrawableClickListener

class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var actionX: Int = 0

    var actionY: Int = 0

    var clickListener: OnDrawableClickListener? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        var bounds: Rect?
        if (event.action == MotionEvent.ACTION_DOWN) {
            actionX = event.x.toInt()
            actionY = event.y.toInt()

            compoundDrawables[2]?.run {
                bounds = this.bounds

                //4: paddingRight in ItemInput view
                val extraTapArea = (4 * resources.displayMetrics.density + 0.5).toInt()

                val x: Int = width - actionX - extraTapArea
                val y: Int = actionY - (height - bounds!!.height()) / 2

                /**If drawble bounds contains the x and y points then move ahead. */
                if (bounds!!.contains(x, y)) {
                    clickListener?.let {
                        it.onClick(
                            this@CustomEditText,
                            OnDrawableClickListener.DrawablePosition.RIGHT
                        )

                        return false
                    }
                    return false
                }
                return super.onTouchEvent(event)
            }
        }
        return super.onTouchEvent(event)
    }

//    @Throws(Throwable::class)
//  override  protected fun finalize() {
//        drawableRight = null
//        drawableBottom = null
//        drawableLeft = null
//        drawableTop = null
//        super.f
//    }
}