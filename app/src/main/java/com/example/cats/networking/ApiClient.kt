package com.example.cats.networking

import com.example.cats.breeds.BreedImageUrl
import com.example.cats.breeds.CatBreed
import com.example.cats.breeds.LoginResponse
import com.example.cats.login.LoginUser
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiClient {

    @GET("breeds")
    fun getRepo(): Single<List<CatBreed>>

    @GET("images/search")
    fun getBreedImageUrl(
        @Query("breed_id") breedId: String, @Query("size") size: String,
        @Query("limit") limit: Int
    ): Single<List<BreedImageUrl>>

    @POST("login")
    fun login(@Body credentials: LoginUser): Single<LoginResponse>

}
