package com.antino.avengers.koin.appModule

import com.antino.avengers.LoginViewModel
import com.antino.avengers.presentation.ViewModel.DeveloperViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel


val ViewModelModule = module {
    viewModel { LoginViewModel<Any?>(get()) }

    viewModel { DeveloperViewModel<Any?>(get()) }
}