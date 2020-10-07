package com.example.cats.breeds

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.cats.BR
import com.google.gson.annotations.SerializedName


data class CatBreed(
    val name: String,
    val description: String,
    @SerializedName("country_code")
    val countryCode: String,
    var _url: String,
    val id: String,
    @SerializedName("wikipedia_url")
    val wikipediaUrl: String,
    val temperament: String

) : BaseObservable() {

    var url: String
        @Bindable get() = _url
        set(value) {
            _url = value
            notifyPropertyChanged(BR.url)
        }
}

data class BreedImageUrl(val url: String)

data class LoginResponse(val userToken: String)