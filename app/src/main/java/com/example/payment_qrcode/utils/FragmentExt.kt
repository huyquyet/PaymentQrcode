package com.example.payment_qrcode.utils

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.payment_qrcode.R

@SuppressLint("WrongConstant")
private fun commitTransaction(
    transaction: FragmentTransaction,
    addToBackStack: Boolean = false,
    isTransaction: Boolean = false,
    name: String? = null
) {
    Log.d("-------------: ", "FragmentExt commitTransaction: isTransaction -> $isTransaction")
    if (isTransaction) {
        transaction.setCustomAnimations(R.anim.enter_from_right, 0, 0, R.anim.exit_to_right)
    }
    if (addToBackStack) transaction.addToBackStack(name)
    transaction.commit()
}

fun Fragment.addFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    containerViewId: Int,
    TAG: String?,
    addToBackStack: Boolean = true,
    isTransaction: Boolean = false
) {
    fragmentManager.beginTransaction()
        .add(containerViewId, fragment, TAG)
        .apply {
            commitTransaction(this, addToBackStack, isTransaction, TAG)
        }
}

fun Fragment.replaceFragment(
    fragmentManager: FragmentManager,
    childFragment: Fragment,
    containerViewId: Int,
    TAG: String?,
    addToBackStack: Boolean = true,
    isTransaction: Boolean = false
) {
    fragmentManager.beginTransaction().apply {
//        if (isTransaction) {
//            setCustomAnimations(R.anim.eter_from_right,0, 0, R.anim.exit_to_right)
//        }
        replace(containerViewId, childFragment, TAG)
        if (addToBackStack) addToBackStack(TAG)
    }.commit()
}

fun Fragment.showToast(string: String?) {
    Toast.makeText(context, string, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(@StringRes int: Int) {
    Toast.makeText(context, int, Toast.LENGTH_SHORT).show()
}