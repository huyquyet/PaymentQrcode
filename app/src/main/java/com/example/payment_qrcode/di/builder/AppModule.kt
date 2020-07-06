package com.example.payment_qrcode.di.builder

import android.content.Context
import com.example.payment_qrcode.MainApplication
import com.example.payment_qrcode.data.RepositoryModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, FragmentBuildersModule::class, RepositoryModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providerContext(application: MainApplication): Context {
        return application.applicationContext
    }
}