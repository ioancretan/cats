package com.example.cats.di.component

import com.example.cats.breeds.CatsBreedsViewModel
import com.example.cats.di.module.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class)])
interface NetWorkComponent {

    fun inject(breedsViewModel: CatsBreedsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): NetWorkComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }
}