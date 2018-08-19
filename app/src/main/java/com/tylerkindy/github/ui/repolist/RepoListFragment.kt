package com.tylerkindy.github.ui.repolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tylerkindy.github.R
import com.tylerkindy.github.di.OauthToken
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repo_list.*
import javax.inject.Inject

class RepoListFragment : DaggerFragment() {

    @Inject
    lateinit var oauthToken: OauthToken

    private val section = Section()
    private val adapter = GroupAdapter<ViewHolder>().apply { add(section) }

    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = RepoListViewModel(oauthToken.token)
        val sub = viewModel.getRepos()
                .map { response ->
                    response.data()?.viewer()?.repositories()?.edges()?.map {
                        RepoItem(it.node()?.name() ?: throw IllegalArgumentException("Name is null!"))
                    } ?: emptyList()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(section::update)

        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repoList.adapter = adapter
        repoList.layoutManager = layoutManager
    }
}
