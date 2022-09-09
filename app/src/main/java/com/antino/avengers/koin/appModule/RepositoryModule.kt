package com.antino.avengers.koin.appModule

import com.antino.avengers.LoginRepository
import org.koin.dsl.module

val RepositoryModule = module {


    single { LoginRepository(get()) }



}


