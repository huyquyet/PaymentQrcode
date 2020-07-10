package com.example.payment_qrcode.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.payment_qrcode.ShareViewModel
import com.example.payment_qrcode.base.ViewModelProviderFactory
import com.example.payment_qrcode.di.annotation.ViewModelKey
import com.example.payment_qrcode.ui.screen.main.MainViewModel
import com.example.payment_qrcode.ui.screen.payment.PaymentViewModel
import com.example.payment_qrcode.ui.screen.profile.ProfileViewModel
import com.example.payment_qrcode.ui.screen.scan.ScanViewModel
import com.example.payment_qrcode.ui.screen.signin.LoginViewModel
import com.example.payment_qrcode.ui.screen.signup.SignUpViewModel
import com.example.payment_qrcode.ui.screen.splash.SplashViewModel
import com.example.payment_qrcode.ui.screen.wallet.WalletViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ShareViewModel::class)
    abstract fun bindShareViewModel(shareViewModel: ShareViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WalletViewModel::class)
    abstract fun bindWalletViewModel(walletViewModel: WalletViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SignUpViewModel::class)
    abstract fun bindSignUpViewModel(signUpViewModel: SignUpViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ScanViewModel::class)
    abstract fun bindScanViewModel(scanViewModel: ScanViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel::class)
    abstract fun bindPaymentViewModel(paymentViewModel: PaymentViewModel): ViewModel
}