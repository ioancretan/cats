package com.example.cats.breeds

import android.annotation.SuppressLint
import com.example.cats.di.component.DaggerNetWorkComponent
import com.example.cats.di.component.NetWorkComponent
import com.example.cats.di.module.NetworkModule
import com.example.cats.login.ApiService
import com.example.cats.networking.RxSingleSchedulers
import javax.inject.Inject

class CatsBreedsRepository {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var rxSingleSchedulers: RxSingleSchedulers

    @SuppressLint("CheckResult")
    fun getBreeds( onSucces: (response: List<CatBreed>) -> Unit,
        onError: ((Throwable: Throwable) -> Unit)
    ) {
        apiService.getBreeds()
            .map { it.sortedBy { it.name } }
            .compose(rxSingleSchedulers.applySchedulers())
            .subscribe(
                { result -> onSucces(result) },
                { onError(it) }
            )
    }


    @SuppressLint("CheckResult")
    fun getBreedImageUrl( breedId: String,
                          size: String,
                          limit: Int,
                          onSucces: (response: String) -> Unit,
                   onError: ((Throwable: Throwable) -> Unit)
    ) {
        apiService.getBreedImageUrl(breedId, size, limit)
            .compose(rxSingleSchedulers.applySchedulers())
            .map { it.get(0).url }
            .subscribe(
                { result -> onSucces(result) },
                { onError(it) }
            )
    }

    private val injector: NetWorkComponent = DaggerNetWorkComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is CatsBreedsRepository -> injector.inject(this)
        }
    }

}