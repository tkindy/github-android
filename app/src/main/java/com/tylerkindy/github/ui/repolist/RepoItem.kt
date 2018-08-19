package com.tylerkindy.github.ui.repolist

import com.tylerkindy.github.R
import com.tylerkindy.github.RepositoriesQuery
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_repo.*

data class RepoItem(
  private val repo: RepositoriesQuery.Node
) : Item() {

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.repoName.text = repo.name()
    viewHolder.repoStarCount.text = repo.stargazers().totalCount().toString()
    viewHolder.repoForkCount.text = repo.forks().totalCount().toString()
  }

  override fun getId() = repo.id().hashCode().toLong()

  override fun getLayout() = R.layout.item_repo
}
