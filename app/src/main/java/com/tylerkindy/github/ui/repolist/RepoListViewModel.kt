package com.tylerkindy.github.ui.repolist

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import com.tylerkindy.github.RepositoriesQuery
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoListViewModel @Inject constructor(
        private val apolloClient: ApolloClient
) : ViewModel() {

    fun getRepos(): Observable<Response<RepositoriesQuery.Data>> {
        return Rx2Apollo.from(
                apolloClient.query(
                        RepositoriesQuery.builder().build()
                )
        )
                .subscribeOn(Schedulers.io())
    }
}
