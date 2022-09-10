package com.antino.avengers.koin.appModule

import com.antino.avengers.LoginRepository
import com.antino.avengers.presentation.Repository.DeveloperRepository
import org.koin.dsl.module

val RepositoryModule = module {


    single { LoginRepository(get()) }
    single { DeveloperRepository(get()) }

}


