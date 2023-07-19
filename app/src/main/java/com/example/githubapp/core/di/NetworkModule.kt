package com.example.githubapp.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.example.githubapp.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext

private const val TIMEOUT: Long = 20
private val TIMEOUT_UNIT = TimeUnit.SECONDS
private const val CACHE_SIZE = 10 * 1024 * 1024L // 10MB

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    companion object {

        @Provides
        @Singleton
        fun provideOkhttp(
            @ApplicationContext context: Context,
        ): OkHttpClient {
            val builder = OkHttpClient.Builder()
                .connectTimeout(TIMEOUT, TIMEOUT_UNIT)
                .cache(Cache(context.cacheDir, CACHE_SIZE))
            if (BuildConfig.DEBUG) {
                builder.addNetworkInterceptor(
                    HttpLoggingInterceptor().setLevel(BODY)
                )
            }
            return builder.build()
        }
    }
}
