package com.example.android.bakingapp.tabs


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bakingapp.IngredientsTabAdapter
import com.example.android.bakingapp.databinding.FragmentIngredientBinding
import com.example.android.bakingapp.network.Ingredient
import com.example.android.bakingapp.network.Recipe

/**
 * A simple [Fragment] subclass.
 */
class IngredientFragment : Fragment() {

//    private val viewModel: IngredientListViewModel by lazy {
//        ViewModelProviders.of(this).get(IngredientListViewModel::class.java)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val ingredients = arguments?.getParcelableArrayList<Ingredient>("Ingredients") as ArrayList<Ingredient>

        val ingredientAdapter = IngredientsTabAdapter(arrayListOf())

//        ingredientAdapter.updateList(ingredients)

        val binding = FragmentIngredientBinding.inflate(inflater)
        binding.lifecycleOwner = this

//        binding.ingredientTabRecycler.adapter = ingredientAdapter

        binding.ingredientTabRecycler.apply {

            layoutManager = LinearLayoutManager(activity)
            adapter = ingredientAdapter

            binding.executePendingBindings()
        }

        return binding.root
    }
}
