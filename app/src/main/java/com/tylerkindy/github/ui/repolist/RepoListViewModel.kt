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

class RepoListViewModel : ViewModel() {

    val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor())
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

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        return if (request.url().host() != "api.github.com") {
            chain.proceed(request)
        } else {
            val newRequest = request.newBuilder()
                    .addHeader("Authorization", "bearer 3bdec38e170d411349bd2a52344d294a3192c7e5")
                    .build()

            chain.proceed(newRequest)
        }
    }
}
