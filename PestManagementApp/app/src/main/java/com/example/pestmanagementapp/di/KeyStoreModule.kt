package com.example.pestmanagementapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import android.content.Context
import com.example.pestmanagementapp.utils.KeystoreManager

@Module
@InstallIn(SingletonComponent::class)
object KeystoreModule {

    @Provides
    fun provideKeystoreManager(context: Context): KeystoreManager {
        return KeystoreManager(context)
    }
}