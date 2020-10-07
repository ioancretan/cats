package com.example.cats.breeds

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cats.breeds.adapter.CatsBreedsListAdapter
import com.example.cats.databinding.FragmentCatsBreedsBinding
import kotlinx.android.synthetic.main.fragment_cats_breeds.*


class CatsBreedsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentCatsBreedsBinding
    private lateinit var adapter: CatsBreedsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewDataBinding = FragmentCatsBreedsBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProvider(requireActivity()).get(CatsBreedsViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBreedsAdapter()
        setupObservers()
        setUpListeners()

        viewDataBinding.viewmodel?.fetchCatsBreedsList()

    }

    private fun setUpListeners() {
        autoCompleteTextView.setOnItemClickListener { _, _, pos, _ ->
            viewDataBinding.viewmodel?.filterBreeds(pos)
        }
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.breedsListLive?.observe(viewLifecycleOwner, Observer {
            adapter.updateBreedsList(it)
        })

        viewDataBinding.viewmodel?.countryeNamesLive?.observe(viewLifecycleOwner, Observer {
            setUpBreedNamesAdapter(it)
        })
    }

    private fun setUpBreedNamesAdapter(it: List<String>) {
        autoCompleteTextView.setAdapter(
            ArrayAdapter(
                this.requireContext(),
                R.layout.select_dialog_item,
                it
            )
        )
    }

    private fun setupBreedsAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            adapter = CatsBreedsListAdapter(viewDataBinding.viewmodel!!)
            val layoutManager = LinearLayoutManager(activity)
            cats_list_rv.layoutManager = layoutManager
            cats_list_rv.addItemDecoration(
                DividerItemDecoration(
                    activity,
                    layoutManager.orientation
                )
            )
            cats_list_rv.adapter = adapter
        }
    }
}