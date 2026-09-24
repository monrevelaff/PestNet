package com.example.pestmanagementapp.di


import android.content.Context
import androidx.camera.lifecycle.ProcessCameraProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CameraModule {

    // Provide the CameraExecutor
    @Provides
    @Singleton
    fun provideCameraExecutor(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    // Provide the ProcessCameraProvider
    @Provides
    @Singleton
    fun provideProcessCameraProvider(context: Context): ProcessCameraProvider {
        return ProcessCameraProvider.getInstance(context).get()
    }
}
