package com.tylerkindy.github.network

import com.tylerkindy.github.di.OauthToken
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
  private val oauthToken: OauthToken
) : Interceptor {
  override fun intercept(chain: Interceptor.Chain): Response {
    val request = chain.request()

    return if (request.url().host() != "api.github.com") {
      chain.proceed(request)
    } else {
      val newRequest = request.newBuilder()
        .addHeader("Authorization", "bearer ${oauthToken.token}")
        .build()

      chain.proceed(newRequest)
    }
  }
}
