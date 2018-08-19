package com.tylerkindy.github.di

import android.content.Context
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides

@Module
class ConfigModule(private val appContext: Context) {

    @Provides
    fun provideAppContext() = appContext

    @Provides
    fun provideOauthToken(appContext: Context): OauthToken {
        val token = appContext.packageManager.getApplicationInfo(
                appContext.packageName,
                PackageManager.GET_META_DATA
        ).metaData["oauthToken"] as String

        return OauthToken(token)
    }
}

data class OauthToken(val token: String)
