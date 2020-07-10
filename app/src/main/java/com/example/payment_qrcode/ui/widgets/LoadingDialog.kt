package com.example.payment_qrcode.ui.widgets

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.view.animation.AnimationUtils
import androidx.fragment.app.DialogFragment
import com.example.payment_qrcode.R

class LoadingDialog : DialogFragment() {
    private var rootView: View? = null

    override fun onStart() {
        super.onStart()
        val animation = AnimationUtils.loadAnimation(context, R.anim.anim_loading_show)
        rootView?.visibility = View.VISIBLE
        rootView?.startAnimation(animation)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        val root: View = inflater.inflate(R.layout.loading_dialog, null)

        //Customizing the dialog features
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(root)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!
            .setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }
}