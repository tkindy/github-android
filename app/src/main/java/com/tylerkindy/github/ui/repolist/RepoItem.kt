package com.tylerkindy.github.ui.repolist

import com.tylerkindy.github.R
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_repo.*

data class RepoItem(private val repoName: String) : Item() {

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.repoName.text = repoName
  }

  override fun getId() = repoName.hashCode().toLong()

  override fun getLayout() = R.layout.item_repo
}
