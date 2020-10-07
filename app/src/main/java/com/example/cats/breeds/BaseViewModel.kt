package com.example.cats.breeds

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cats.di.component.DaggerNetWorkComponent
import com.example.cats.di.component.NetWorkComponent
import com.example.cats.di.module.NetworkModule

open class BaseViewModel : ViewModel() {

    val dataLoading = MutableLiveData<Boolean>().apply { value = false }

    private val injector : NetWorkComponent = DaggerNetWorkComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is CatsBreedsViewModel -> injector.inject(this)
        }
    }
}


