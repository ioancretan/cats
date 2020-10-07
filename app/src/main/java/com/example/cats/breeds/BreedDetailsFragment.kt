package com.example.cats.breeds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cats.databinding.FragmentBreedDetailsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class BreedDetailsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentBreedDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentBreedDetailsBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProvider(requireActivity()).get(CatsBreedsViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}