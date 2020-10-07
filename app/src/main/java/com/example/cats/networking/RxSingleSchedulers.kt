package com.example.cats.networking

import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


interface RxSingleSchedulers {
    fun <T> applySchedulers(): SingleTransformer<T, T>?

    companion object {
        val DEFAULT: RxSingleSchedulers = object : RxSingleSchedulers {
            override fun <T> applySchedulers(): SingleTransformer<T, T> {
                return SingleTransformer { single: Single<T> ->
                    single
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                }
            }
        }
        val TEST_SCHEDULER: RxSingleSchedulers = object : RxSingleSchedulers {
            override fun <T> applySchedulers(): SingleTransformer<T, T> {
                return SingleTransformer { single: Single<T> ->
                    single
                        .subscribeOn(Schedulers.trampoline())
                        .observeOn(Schedulers.trampoline())
                }
            }
        }
    }
}