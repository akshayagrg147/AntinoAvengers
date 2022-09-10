package com.antino.avengers.koin.appModule

import com.antino.avengers.LoginRepository
import com.antino.avengers.ManagerByIdRepository
import com.antino.avengers.ProjectManagersModel
import org.koin.dsl.module

val RepositoryModule = module {


    single { LoginRepository(get()) }
//    single { ManagerByIdRepository(get()) }



}


