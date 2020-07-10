package com.example.payment_qrcode.ui.screen.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.payment_qrcode.data.Constants

class MainPagerAdapter(
    fm: FragmentManager,
    private val listFragment: List<Fragment>
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = listFragment[position]

    override fun getCount(): Int = Constants.NUMBER_TAB_HOME
}