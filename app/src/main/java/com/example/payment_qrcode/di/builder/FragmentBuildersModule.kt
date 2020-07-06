package com.example.payment_qrcode.di.builder

import com.example.payment_qrcode.MainActivity
import com.example.payment_qrcode.screen.main.MainFragment
import com.example.payment_qrcode.screen.signin.LoginFragment
import com.example.payment_qrcode.screen.splash.SplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeSplashFragment(): SplashFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}
