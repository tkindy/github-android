package com.tylerkindy.github.network

import com.apollographql.apollo.ApolloClient
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named

@Module
class NetworkModule {

  @Provides
  @Named(GITHUB_CLIENT)
  fun provideGitHubClient(tokenInterceptor: TokenInterceptor): OkHttpClient =
    OkHttpClient.Builder()
      .addInterceptor(tokenInterceptor)
      .build()

  @Provides
  fun provideApolloClient(@Named(GITHUB_CLIENT) githubClient: OkHttpClient): ApolloClient =
    ApolloClient.builder()
      .serverUrl("https://api.github.com/graphql")
      .okHttpClient(githubClient)
      .build()

  companion object {
    const val GITHUB_CLIENT = "GITHUB_CLIENT"
  }
}
