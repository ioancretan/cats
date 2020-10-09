package com.example.cats.di.component

import com.example.cats.breeds.CatsBreedsRepository
import com.example.cats.breeds.CatsBreedsViewModel
import com.example.cats.di.module.NetworkModule
import com.example.cats.login.LoginRepository
import com.example.cats.login.ApiService
import com.example.cats.login.LoginViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface NetWorkComponent {

    fun inject(loginViewModel: LoginViewModel)

    fun inject(breedsViewModel: CatsBreedsViewModel)

    fun inject(rxSingleSchedulers: LoginRepository)

    fun inject(retrofit: ApiService)

    fun inject(retrofit: CatsBreedsRepository)

}