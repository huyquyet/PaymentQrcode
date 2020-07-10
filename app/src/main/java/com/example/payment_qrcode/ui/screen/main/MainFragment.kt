package com.example.payment_qrcode.ui.screen.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.data.Constants
import com.example.payment_qrcode.databinding.FragmentMainBinding
import com.example.payment_qrcode.ui.screen.profile.ProfileFragment
import com.example.payment_qrcode.ui.screen.scan.ScanFragment
import com.example.payment_qrcode.ui.screen.wallet.WalletFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    override val viewModel: MainViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_main

    private val onPageChange = object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
        }
    }

    /**
     * Call in [onViewCreated] when view has created
     */
    override fun initData() {
        viewDataBinding.bottomMain.setOnNavigationItemSelectedListener(this)

        val listFragment = ArrayList<Fragment>().apply {
            add(WalletFragment.newInstance())
            add(ScanFragment.newInstance())
            add(ProfileFragment.newInstance())
        }

        val contentPagerAdapter = MainPagerAdapter(
            childFragmentManager,
            listFragment
        )
        viewDataBinding.viewPagerHome.apply {
            adapter = contentPagerAdapter
            offscreenPageLimit = Constants.NUMBER_TAB_HOME
            addOnPageChangeListener(onPageChange)
        }
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        shareViewModel.switchViewPager.observe(viewLifecycleOwner, Observer { position ->
            if (position in 0..2) {
                viewDataBinding.bottomMain.selectedItemId = R.id.navigationWallet
            }
        })
    }

    /**
     * Called when an item in the bottom navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item and false if the item should not be
     * selected. Consider setting non-selectable items as disabled preemptively to make them
     * appear non-interactive.
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigationWallet -> setCurrentPage(0)
            R.id.navigationScan -> setCurrentPage(1)
            R.id.navigationProfile -> setCurrentPage(2)
        }
        return true
    }

    private fun setCurrentPage(position: Int) {
        if (position > Constants.NUMBER_TAB_HOME - 1) return
        viewDataBinding.viewPagerHome.currentItem = position
    }
}