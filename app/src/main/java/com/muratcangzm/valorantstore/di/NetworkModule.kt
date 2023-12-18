package com.muratcangzm.valorantstore.di

import android.os.Build.VERSION_CODES.BASE
import com.muratcangzm.valorantstore.service.ValorantAPI
import com.muratcangzm.valorantstore.utils.Constans.BASE_URL
import com.muratcangzm.valorantstore.utils.NetworkInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideAPI(): ValorantAPI {

        val client: OkHttpClient by lazy {

            OkHttpClient.Builder()
                .addInterceptor(NetworkInterceptor())
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ValorantAPI::class.java)

    }




}