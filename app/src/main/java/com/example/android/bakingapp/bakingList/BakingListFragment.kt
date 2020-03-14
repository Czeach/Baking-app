package com.example.android.bakingapp.bakingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bakingapp.R
import com.example.android.bakingapp.databinding.FragmentBakingListBinding
import com.example.android.bakingapp.network.Recipe
import kotlinx.android.synthetic.main.fragment_baking_list.*
import java.util.ArrayList

class BakingListFragment : Fragment() {

//    private lateinit var viewModel: BakingListViewModel

    var recipeAdapter = BakingListAdapter(arrayListOf())

    private val viewModel: BakingListViewModel by lazy {
        ViewModelProviders.of(this).get(BakingListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentBakingListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_baking_list, container, false
        )

        binding.setLifecycleOwner(this)

        binding.homeListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recipeAdapter
        }

        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            recipeAdapter.updateList(it)
        })

        return binding.root
    }

}
