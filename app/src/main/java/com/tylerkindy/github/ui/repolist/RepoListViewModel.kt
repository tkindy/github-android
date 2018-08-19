package com.tylerkindy.github.ui.repolist

import androidx.lifecycle.ViewModel
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.Rx2Apollo
import com.tylerkindy.github.RepositoriesQuery
import com.tylerkindy.github.network.GraphQLQueryException
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoListViewModel @Inject constructor(
  private val apolloClient: ApolloClient
) : ViewModel() {

  fun getRepoItems(): Observable<List<RepoItem>> {
    return Rx2Apollo.from(
      apolloClient.query(
        RepositoriesQuery.builder().build()
      )
    )
      .subscribeOn(Schedulers.io())
      .map { response ->
        if (response.hasErrors()) {
          throw GraphQLQueryException(response.errors())
        }

        val repos = response.data()!!
          .viewer()
          .repositories()
          .edges()
          ?.mapNotNull(RepositoriesQuery.Edge::node) ?: emptyList()

        repos.map(::RepoItem)
      }
      .observeOn(AndroidSchedulers.mainThread())
  }
}
