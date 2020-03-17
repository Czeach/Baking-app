package com.example.android.bakingapp.ingredientList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.android.bakingapp.R
import com.example.android.bakingapp.databinding.FragmentIngredientListBinding

class IngredientListFragment : Fragment() {

//    var ingredientAdapter = IngredientAdapter(arrayListOf())

    private val viewModel: IngredientListViewModel by lazy {
        ViewModelProviders.of(this).get(IngredientListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

//        val application = requireNotNull(activity).application
//        val ingredient = IngredientListFragmentArgs.fromBundle(arguments!!).selectedRecipe
//        val viewModelFactory = IngredientListViewModelFactory(ingredient, application)

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentIngredientListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_ingredient_list, container, false
        )
//        val binding = FragmentIngredientListBinding.inflate(inflater)

//        binding.ingredientListViewModel = ViewModelProviders.of(
//            this, viewModelFactory).get(IngredientListViewModel::class.java)

        binding.setLifecycleOwner(this)

//        binding.ingredientListViewModel = viewModel

//        binding.ingredientRecycler.apply {
//            layoutManager = LinearLayoutManager(activity)
//            adapter = ingredientAdapter
//
//            binding.executePendingBindings()
//        }

//        viewModel.selectedRecipe.observe(viewLifecycleOwner, Observer {
//            ingredientAdapter.updateIngredientList(it)
//        })


        return binding.root
    }
}
