package com.example.cats.login

import com.example.cats.breeds.BreedImageUrl
import com.example.cats.breeds.CatBreed
import com.example.cats.breeds.LoginResponse
import com.example.cats.networking.ApiClient
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

class ApiService
@Inject constructor(retrofit: Retrofit) : ApiClient {
    private val breedsApi by lazy { retrofit.create(ApiClient::class.java) }

    override fun getBreeds(): Single<List<CatBreed>> {
        return breedsApi.getBreeds()
    }

    override fun getBreedImageUrl(
        breedId: String,
        size: String,
        limit: Int
    ): Single<List<BreedImageUrl>> {
        return breedsApi.getBreedImageUrl(breedId, size, limit)
    }

    override fun login(credentials: LoginUser): Single<LoginResponse> {
//        return breedsApi.login(credentials)

        return mockLoginApiCall()
    }

    fun mockLoginApiCall(): Single<LoginResponse> {
        return Single.just(LoginResponse("token"))
    }
}