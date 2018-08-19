package com.tylerkindy.github.network

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
class NetworkModule {

    @Provides
    @Named(GITHUB_CLIENT)
    fun provideGitHubClient(tokenInterceptor: TokenInterceptor) =
            OkHttpClient.Builder()
                    .addInterceptor(tokenInterceptor)
                    .build()

    companion object {
        const val GITHUB_CLIENT = "GITHUB_CLIENT"
    }
}
