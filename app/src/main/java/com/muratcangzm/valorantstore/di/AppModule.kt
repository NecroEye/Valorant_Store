package com.muratcangzm.valorantstore.di

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.muratcangzm.valorantstore.R
import com.muratcangzm.valorantstore.model.remote.CurrencyModel
import com.muratcangzm.valorantstore.model.remote.WeaponryModel
import com.muratcangzm.valorantstore.views.adapters.WeaponryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }


    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) =
        Glide.with(context)
            .setDefaultRequestOptions(
                RequestOptions().placeholder(R.drawable.not_found)
                    .error(R.drawable.not_found)
            )


}