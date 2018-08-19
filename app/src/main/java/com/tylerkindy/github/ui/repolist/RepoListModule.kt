package com.tylerkindy.github.ui.repolist

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface RepoListModule {

    @ContributesAndroidInjector
    fun contributeFragment(): RepoListFragment
}
