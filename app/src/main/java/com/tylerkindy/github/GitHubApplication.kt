package com.tylerkindy.github

import com.tylerkindy.github.di.ConfigModule
import com.tylerkindy.github.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class GitHubApplication : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .configModule(ConfigModule(applicationContext))
      .build()
  }
}
