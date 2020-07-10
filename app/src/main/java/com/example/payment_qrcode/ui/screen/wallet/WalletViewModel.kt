package com.example.payment_qrcode.ui.screen.wallet

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.payment_qrcode.R
import com.example.payment_qrcode.base.BaseViewModel
import com.example.payment_qrcode.data.model.Balance
import com.example.payment_qrcode.data.model.ItemWallet
import com.example.payment_qrcode.data.model.TypeItemWallet
import com.example.payment_qrcode.data.model.User
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.utils.*
import javax.inject.Inject
import kotlin.random.Random

class WalletViewModel @Inject constructor(
    val context: Context,
    val userRepository: UserRepository
) : BaseViewModel() {
    private val _dataRecycler = MutableLiveData<MutableList<ItemWallet>>()
    val dataRecycler: LiveData<MutableList<ItemWallet>>
        get() = _dataRecycler

    private val _onClickTopUp = MutableLiveData<Boolean>()
    val onClickTopUp: LiveData<Boolean>
        get() = _onClickTopUp

    private val _onClickVault = MutableLiveData<Boolean>()
    val onClickVault: LiveData<Boolean>
        get() = _onClickVault


    var lvdBalance = MutableLiveData<String>().apply { value = "0.00" }

    fun onClick(v: View) {
        when (v.id) {
            R.id.img_top_up -> {
                _onClickTopUp.value = true
            }

            R.id.img_vault -> {
                _onClickVault.value = true
            }
        }
    }

    fun loadPaymentAvailable() {
        val user = userRepository.getUserInfo()
        val ba = checkBalanceExists(user)
        val balance = if (ba == -1.00) 10000.00 else ba

        saveBalances(userRepository, balance)
        lvdBalance.value = balance.formatPrice()
    }

    fun loadMore() {
        _dataRecycler.value = createData(10)
    }

    fun refreshData() {
        _dataRecycler.value = createData(20)
    }

    fun loadData() {
        _dataRecycler.value = createData(20)
    }

    private fun createData(count: Int): MutableList<ItemWallet> {
        val names = getNameVault()
        val description = getDescriptionVault()
        val images = getImageVault()
        val data = ArrayList<ItemWallet>()
        for (i in 0 until count) {
            val number = Random.nextInt(0, 3)
            val time = System.currentTimeMillis()
            val itemWallet = if (i % 4 == 0) {
                createItemWalletDate()
            } else {
                ItemWallet(
                    id = time,
                    name = names[number],
                    description = description[number],
                    price = randomNumberDouble1(100),
                    reason = if (i % 3 == 0) "Under Risk Review" else "",
                    imageUrl = images[number],
                    type = TypeItemWallet.ITEM
                )
            }
            data.add(itemWallet)
        }
        return data
    }

    private fun getNameVault(): MutableList<String> {
        return context.resources.getStringArray(R.array.name_vault).toMutableList()
    }

    private fun getDescriptionVault(): MutableList<String> {
        return context.resources.getStringArray(R.array.description_vault).toMutableList()
    }

    private fun getImageVault(): MutableList<Int> {
        return ArrayList<Int>().apply {
            add(R.drawable.image_1)
            add(R.drawable.image_2)
            add(R.drawable.image_3)
            add(R.drawable.image_4)
        }
    }


    private fun getDate(): MutableList<String> {
        return ArrayList<String>().apply {
            add("04.19.2020")
            add("04.20.2020")
            add("04.21.2020")
            add("04.19.2021")
        }
    }

    private fun createItemWalletDate(): ItemWallet {
        val number = Random.nextInt(0, 3)
        val dates = getDate()
        return ItemWallet(
            id = System.currentTimeMillis(),
            name = "",
            description = "",
            price = 0.0,
            reason = "",
            imageUrl = 0,
            type = TypeItemWallet.TIME,
            date = dates[number]
        )
    }

    private fun checkBalanceExists(user: User?): Double {
        val ba = getBalance(userRepository)
        return ba?.balance ?: -1.00
    }
}