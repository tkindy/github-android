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

  sealed class Status {
    object Loading : Status()
    data class Loaded(val repoItems: List<RepoItem>) : Status()
  }

  fun getRepoItems(): Observable<Status> {
    return Rx2Apollo.from(
      apolloClient.query(
        RepositoriesQuery.builder().build()
      )
    )
      .subscribeOn(Schedulers.io())
      .map<Status> { response ->
        if (response.hasErrors()) {
          throw GraphQLQueryException(response.errors())
        }

        val repos = response.data()!!
          .viewer()
          .repositories()
          .edges()
          ?.mapNotNull(RepositoriesQuery.Edge::node) ?: emptyList()

        Status.Loaded(repos.map(::RepoItem))
      }
      .startWith(Status.Loading)
      .observeOn(AndroidSchedulers.mainThread())
  }
}
