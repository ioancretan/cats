package com.example.cats.login

import android.annotation.SuppressLint
import com.example.cats.breeds.LoginResponse
import com.example.cats.di.component.DaggerNetWorkComponent
import com.example.cats.di.component.NetWorkComponent
import com.example.cats.di.module.NetworkModule
import com.example.cats.networking.RxSingleSchedulers
import javax.inject.Inject

class LoginRepository {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var rxSingleSchedulers: RxSingleSchedulers

    @SuppressLint("CheckResult")
    fun login(
        credentials: LoginUser, onSucces: (loginResponse: LoginResponse) -> Unit,
        onError: ((Throwable: Throwable) -> Unit)
    ) {
        apiService.login(credentials)
            .compose(rxSingleSchedulers.applySchedulers())
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
            is LoginRepository -> injector.inject(this)
        }
    }

}