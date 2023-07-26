package com.example.githubapp.core.di

import android.content.Context
import arrow.retrofit.adapter.either.EitherCallAdapterFactory
import com.example.githubapp.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val TIMEOUT: Long = 20
private val TIMEOUT_UNIT = TimeUnit.SECONDS
private const val CACHE_SIZE = 10 * 1024 * 1024L // 10MB
private const val GITHUB_AUTH_BASE_URL = """"https://github.com/login/oauth/access_token"""

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
                .addNetworkInterceptor(createOkHttpHeaderInterceptor())
                .connectTimeout(TIMEOUT, TIMEOUT_UNIT)
                .cache(Cache(context.cacheDir, CACHE_SIZE))
            if (BuildConfig.DEBUG) {
                builder.addNetworkInterceptor(
                    HttpLoggingInterceptor().setLevel(BODY)
                )
            }
            return builder.build()
        }

        @Provides
        @Singleton
        fun provideAuthRetrofit(client: OkHttpClient): Retrofit {
            ContentNegotiation
            return Builder()
                .baseUrl(GITHUB_AUTH_BASE_URL)
                .addCallAdapterFactory(EitherCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build()
        }
    }

    private fun createOkHttpHeaderInterceptor() =
        Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder();
            requestBuilder.header("Content-Type", "application/json");
            chain.proceed(requestBuilder.build())
        }
}
