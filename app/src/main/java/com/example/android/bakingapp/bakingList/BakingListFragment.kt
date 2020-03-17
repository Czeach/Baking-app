package com.example.android.bakingapp.bakingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bakingapp.BakingListAdapter
import com.example.android.bakingapp.databinding.FragmentBakingListBinding
import com.example.android.bakingapp.network.Recipe

class BakingListFragment : Fragment() {

    private var recipeAdapter = BakingListAdapter(arrayListOf(), BakingListAdapter.OnClickListener {
        viewModel.displayRecipeDetail(it)
        Toast.makeText(activity, "Clicked", Toast.LENGTH_SHORT).show()
    })


    private val viewModel: BakingListViewModel by lazy {
        ViewModelProviders.of(this).get(BakingListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
//        val binding: FragmentBakingListBinding = DataBindingUtil.inflate(
//            inflater, R.layout.fragment_baking_list, container, false
//        )

        val binding = FragmentBakingListBinding.inflate(inflater)

        binding.setLifecycleOwner(this)
        
        binding.bakingListViewModel = viewModel

        binding.homeListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recipeAdapter

            binding.executePendingBindings()
        }

        viewModel.recipe.observe(viewLifecycleOwner, Observer {
            recipeAdapter.updateList(it)
        })

        viewModel.navigateToSelectedRecipe.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController().navigate(BakingListFragmentDirections.actionBakingListFragmentToIngredientListFragment(it))
                viewModel.displayRecipeDetailComplete()
            }
        })

        return binding.root
    }
}