package com.example.android.bakingapp.bakingList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bakingapp.MainActivity
import com.example.android.bakingapp.databinding.FragmentBakingListBinding
import com.example.android.bakingapp.network.Recipe

class BakingListFragment : Fragment() {

    private val onClickListener by lazy {
        object : bakingItemClickListener {
            override fun invoke(recipe: Recipe) {
                val argument = BakingListFragmentDirections.actionBakingListFragmentToTabFragment(recipe)
                findNavController().navigate(argument)
            }
        }
    }

    private var viewModel: BakingListViewModel? = null

    private var recipeAdapter =
        BakingListAdapter(arrayListOf(), onClickListener)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        (requireActivity() as MainActivity).title = "Recipes"

        Log.d("", "${ArrayList<Recipe>()}")

        // Get a reference to the binding object and inflate the fragment views.
        val binding = FragmentBakingListBinding.inflate(inflater)

        viewModel = ViewModelProviders.of(this).get(BakingListViewModel::class.java)

        binding.setLifecycleOwner(this)
        
        binding.bakingListViewModel = viewModel

        binding.homeListRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = recipeAdapter

            binding.executePendingBindings()
        }

        viewModel!!.recipe.observe(viewLifecycleOwner, Observer {
            recipeAdapter.updateList(it)
        })

        return binding.root
    }
    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Recipes"
    }
}