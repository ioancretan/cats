package com.example.cats.breeds

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.example.cats.networking.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class CatsBreedsViewModel : BaseViewModel() {

    @Inject
    lateinit var breedsApiBreeds: ApiClient

    var selectedBreed = MutableLiveData<CatBreed>()

    var breedsListLive = MutableLiveData<List<CatBreed>>()

    var countryeNamesLive = MutableLiveData<List<String>>()

    var countryCodesAndNames = HashMap<String, String>()

    lateinit var breedsList: List<CatBreed>

    @SuppressLint("CheckResult")
    fun fetchCatsBreedsList() {

        if (breedsListLive.value.isNullOrEmpty()) {
            dataLoading.value = true
            breedsApiBreeds.getRepo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.sortedBy { it.name } }
                .subscribe(
                    { result -> onRetrieveBreedsListSuccess(result) },
                    { onRetrieveBreedseListError(it) }
                )
        }
    }

    private fun onRetrieveBreedseListError(it: Throwable) {
        TODO("Not yet implemented")
    }

    private fun onRetrieveBreedsListSuccess(catBreeds: List<CatBreed>) {
        dataLoading.value = false
        breedsListLive.value = catBreeds
        breedsList = catBreeds
        countryeNamesLive.value = getCountryNames()
    }

    fun getCountruCodeByCountryName(countryCode: String): String {
        return countryCodesAndNames.get(countryCode)!!
    }

    fun getCountryNameByCountryCode(countryName: String): String {
        val loc = Locale("", countryName)
        return loc.displayCountry
    }

    fun getCountryNames(): List<String> {
        return breedsListLive.value!!.distinctBy { it.countryCode }.map {
            val countryName = getCountryNameByCountryCode(it.countryCode)
            countryCodesAndNames.put(countryName, it.countryCode)
            countryName
        }
    }

    fun filterBreeds(pos: Int) {
        breedsListLive.value = breedsList.filter {
            it.countryCode.equals(
                getCountruCodeByCountryName(
                    countryeNamesLive.value!![pos]
                )
            )
        }
    }

    @SuppressLint("CheckResult")
    fun loadUrlForCatBreed(id: String) {
        breedsApiBreeds.getBreedImageUrl(id,"thumb",0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { it.get(0).url }
            .subscribe(
                { url -> onRetrieveBreedImageSuccess(url, id) },
                { onRetrieveBreedseListError(it) }
            )
    }

    private fun onRetrieveBreedImageSuccess(url: String, id: String) {
        breedsList.find { it.id.equals(id) }!!.url = url
        breedsListLive.value!!.find { it.id.equals(id) }!!.url = url
    }
}

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun setImageUrl(view: ImageView, url: String?) {

        if(url!=null) {
            Glide.with(view.context).load(url).into(view)
        }
    }
}