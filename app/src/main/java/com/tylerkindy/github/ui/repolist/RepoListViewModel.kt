package com.tylerkindy.github.ui.repolist

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.tylerkindy.github.RepositoriesQuery
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

class RepoListViewModel(
        oauthToken: String
) : ViewModel() {

    val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(oauthToken))
            .build()

    val apolloClient = ApolloClient.builder()
            .serverUrl("https://api.github.com/graphql")
            .okHttpClient(okHttpClient)
            .build()

    fun getRepos(): Observable<com.apollographql.apollo.api.Response<RepositoriesQuery.Data>> {
        return Rx2Apollo.from(
                apolloClient.query(
                        RepositoriesQuery.builder().build()
                )
        )
                .subscribeOn(Schedulers.io())
    }
}

class TokenInterceptor(
        private val oauthToken: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return if (request.url().host() != "api.github.com") {
            chain.proceed(request)
        } else {
            val newRequest = request.newBuilder()
                    .addHeader("Authorization", "bearer $oauthToken")
                    .build()

            chain.proceed(newRequest)
        }
    }
}
