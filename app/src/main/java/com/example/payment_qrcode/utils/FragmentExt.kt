package com.example.payment_qrcode.utils

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun Fragment.addFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    containerViewId: Int,
    TAG: String?,
    addToBackStack: Boolean = true,
    transit: Int = -1
) {
    fragmentManager.beginTransaction()
        .add(containerViewId, fragment, TAG)
        .apply {
            commitTransaction(this, addToBackStack, transit, TAG)
        }
}

@SuppressLint("WrongConstant")
private fun commitTransaction(
    transaction: FragmentTransaction,
    addToBackStack: Boolean = false,
    transit: Int = -1,
    name: String? = null
) {
    if (addToBackStack) transaction.addToBackStack(name)
    if (transit != -1) transaction.setTransition(transit)
    transaction.commit()
}

fun Fragment.replaceFragment(
    fragmentManager: FragmentManager,
    childFragment: Fragment,
    containerViewId: Int,
    TAG: String?,
    addToBackStack: Boolean = true,
    transit: Int = -1
) {
    fragmentManager.beginTransaction()
        .replace(containerViewId, childFragment, TAG)
        .apply {
            commitTransaction(this, addToBackStack, transit, TAG)
        }
}