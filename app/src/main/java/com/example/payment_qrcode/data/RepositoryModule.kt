package com.example.payment_qrcode.data

import android.content.Context
import com.example.payment_qrcode.data.local.AppPrefs
import com.example.payment_qrcode.data.local.PrefHelper
import com.example.payment_qrcode.data.local.SharedPrefsApi
import com.example.payment_qrcode.data.repository.UserRepository
import com.example.payment_qrcode.data.repository.impl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providerSharedPrefsApi(context: Context): SharedPrefsApi {
        return SharedPrefsApi(context)
    }

    @Provides
    @Singleton
    fun providerAppPrefs(sharedPrefsApi: SharedPrefsApi): AppPrefs {
        return AppPrefs(sharedPrefsApi)
    }

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun providerUserRepository(repository: UserRepositoryImpl): UserRepository {
        return repository
    }
}
