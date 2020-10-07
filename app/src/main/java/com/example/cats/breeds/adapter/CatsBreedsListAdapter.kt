package com.example.cats.breeds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cats.breeds.CatBreed
import com.example.cats.breeds.CatsBreedsViewModel
import com.example.cats.databinding.ViewCatListItemBinding

class CatsBreedsListAdapter(private val repoListViewModel: CatsBreedsViewModel ) : RecyclerView.Adapter<CatListViewHolder>() {
    var catsBreedsList: List<CatBreed> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ViewCatListItemBinding.inflate(inflater, parent, false)
        return CatListViewHolder(dataBinding, repoListViewModel)
    }

    override fun getItemCount() = catsBreedsList.size

    override fun onBindViewHolder(holder: CatListViewHolder, position: Int) {
        holder.setup(catsBreedsList[position])
    }

    fun updateBreedsList(repoList: List<CatBreed>) {
        this.catsBreedsList = repoList
        notifyDataSetChanged()
    }
}