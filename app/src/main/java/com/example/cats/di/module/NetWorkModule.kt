package com.example.cats.di.module

import com.example.cats.breeds.CatsBreedsRepository
import com.example.cats.login.LoginRepository
import com.example.cats.login.ApiService
import com.example.cats.networking.ApiClient
import com.example.cats.networking.RxSingleSchedulers
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object NetworkModule {

    const val BASE_URL = "https://api.thecatapi.com/v1/"
    private const val NETWORK_CALL_TIMEOUT = 60

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideBreedsApi(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRxSingleSchedulers(): RxSingleSchedulers {
        return RxSingleSchedulers.DEFAULT
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideLoginService(retrofit: Retrofit): ApiService {
        return ApiService(retrofit)
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideLoginRepository(apiService: ApiService): LoginRepository {
        return LoginRepository()
    }

    @Provides
    @Reusable
    @JvmStatic
    internal fun provideCatsBreedsRepository(): CatsBreedsRepository {
        return CatsBreedsRepository()
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun provideRetrofitInterface(): Retrofit {

        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .setPrettyPrinting()
            .create()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(createRequestInterceptorClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createRequestInterceptorClient(): OkHttpClient {
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }


        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(2, TimeUnit.SECONDS)
            .readTimeout(2, TimeUnit.SECONDS)
            .writeTimeout(2, TimeUnit.SECONDS)
            .build()

    }
}