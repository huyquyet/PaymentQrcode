package com.example.payment_qrcode.ui.screen.profile

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.payment_qrcode.MainActivity
import com.example.payment_qrcode.base.BaseFragment
import com.example.payment_qrcode.R
import com.example.payment_qrcode.databinding.FragmentProfileBinding
import com.example.payment_qrcode.utils.showToast

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    override val layoutId: Int = R.layout.fragment_profile

    /**
     * Call in [onViewCreated] when view has created
     */
    override fun initData() {
        viewModel.initData()
    }

    /**
     * Call in [onViewCreated] after [initData]
     */
    override fun observeField() {
        with(viewModel) {
            onLogOut.observe(viewLifecycleOwner, Observer { status ->
                status?.apply {
                    when(this) {
                        StatusLogout.CONFIRM -> confirmLogout()
                        StatusLogout.LOGOUT -> logoutSuccess()
                    }
                }
            })

            onItemClick.observe(viewLifecycleOwner, Observer { data ->
                data?.apply {
                    showToast(this)
                }
            })
        }
    }

    private fun confirmLogout() {
        context?.apply {
            AlertDialog.Builder(this).apply {
                setTitle("Logout application")
                setMessage("Logout application")
                setPositiveButton(android.R.string.yes) { _, _ -> viewModel.logout() }
                setNegativeButton(android.R.string.no) { dialog, _ -> dialog.dismiss() }
            }.show()
        }
    }

    private fun logoutSuccess() {
        (activity as MainActivity).userLogout()
    }
}