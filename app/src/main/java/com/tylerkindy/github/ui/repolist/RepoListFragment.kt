package com.tylerkindy.github.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tylerkindy.github.R
import com.tylerkindy.github.ui.BaseFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_repo_list.*

class RepoListFragment : BaseFragment<RepoListViewModel>() {

  private val adapter = GroupAdapter<ViewHolder>()

  private lateinit var layoutManager: RecyclerView.LayoutManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = getModel()
    layoutManager = LinearLayoutManager(requireContext())
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_repo_list, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    repoList.adapter = adapter
    repoList.layoutManager = layoutManager

    disposables += viewModel.getRepoItems()
      .subscribe {
        when (it) {
          is RepoListViewModel.Status.Loading -> showLoading()
          is RepoListViewModel.Status.Loaded -> showLoaded(it.repoItems)
        }
      }
  }

  private fun showLoading() {
    repoListLoadingGroup.visibility = View.VISIBLE
    repoListLoadedGroup.visibility = View.GONE
  }

  private fun showLoaded(repoItems: List<RepoItem>) {
    adapter.update(repoItems)

    repoListLoadingGroup.visibility = View.GONE
    repoListLoadedGroup.visibility = View.VISIBLE
  }
}
