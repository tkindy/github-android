package com.tylerkindy.github.di

import com.tylerkindy.github.GitHubApplication
import com.tylerkindy.github.network.NetworkModule
import com.tylerkindy.github.ui.repolist.RepoListModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [
    AndroidSupportInjectionModule::class,
    ConfigModule::class,
    NetworkModule::class,
    RepoListModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<GitHubApplication>
