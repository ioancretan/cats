package com.example.cats

import android.app.Application
import com.example.cats.di.component.DaggerNetWorkComponent
import com.example.cats.di.component.NetWorkComponent
import com.example.cats.di.module.NetworkModule

class BreedsApplication : Application(){

   lateinit var netWorkComponent : NetWorkComponent

    override fun onCreate() {
        super.onCreate()
        netWorkComponent = DaggerNetWorkComponent.builder().networkModule(NetworkModule).build()
    }
}