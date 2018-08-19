package com.tylerkindy.github.ui.repolist

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.tylerkindy.github.RepositoriesQuery
import com.tylerkindy.github.network.NetworkModule
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import javax.inject.Inject
import javax.inject.Named

class RepoListViewModel @Inject constructor(
        @Named(NetworkModule.GITHUB_CLIENT) okHttpClient: OkHttpClient
) : ViewModel() {

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
