package com.tylerkindy.github.ui.repolist

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tylerkindy.github.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_repo_list.*

class RepoListFragment : Fragment() {

    private val section = Section()
    private val adapter = GroupAdapter<ViewHolder>().apply { add(section) }

    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val oauthToken = requireContext().packageManager.getApplicationInfo(
                requireContext().packageName,
                PackageManager.GET_META_DATA
        ).metaData["oauthToken"] as String

        val viewModel = RepoListViewModel(oauthToken)
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
