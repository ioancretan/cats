package com.example.cats.breeds.adapter

import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cats.BR
import com.example.cats.R
import com.example.cats.breeds.CatBreed
import com.example.cats.breeds.CatsBreedsViewModel


class CatListViewHolder constructor(private val dataBinding: ViewDataBinding, private val breedsListViewModel: CatsBreedsViewModel)
    : RecyclerView.ViewHolder(dataBinding.root) {

    companion object{
        const val BREED_ID = "BREED_ID"
    }

    fun setup(itemData: CatBreed) {
        dataBinding.setVariable(BR.itemData, itemData)
        dataBinding.executePendingBindings()

        if (itemData.url.isNullOrEmpty()){
            breedsListViewModel.loadUrlForCatBreed(itemData.id)
        }

        itemView.setOnClickListener {
            breedsListViewModel.selectedBreed.value = itemData
            itemView.findNavController()
                .navigate(R.id.action_CatsBreedsFragments_to_BreedDetails)
        }
    }
}