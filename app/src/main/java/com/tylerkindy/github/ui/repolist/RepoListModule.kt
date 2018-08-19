package com.tylerkindy.github.ui.repolist

import androidx.lifecycle.ViewModel
import com.tylerkindy.github.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface RepoListModule {

    @ContributesAndroidInjector
    fun contributeFragment(): RepoListFragment

    @Binds
    @IntoMap
    @ViewModelKey(RepoListViewModel::class)
    fun bindViewModel(vm: RepoListViewModel): ViewModel
}
