package com.tylerkindy.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {

  @Inject
  lateinit var viewModelFactory: ViewModelProvider.Factory

  protected lateinit var viewModel: VM
  protected val disposables = CompositeDisposable()

  protected inline fun <reified VM : ViewModel> getModel() =
    viewModelFactory.create(VM::class.java)

  override fun onDestroy() {
    super.onDestroy()
    disposables.clear()
  }
}
