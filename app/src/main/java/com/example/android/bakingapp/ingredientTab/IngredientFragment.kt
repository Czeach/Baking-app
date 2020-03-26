package com.example.android.bakingapp.ingredientTab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.bakingapp.MainActivity
import com.example.android.bakingapp.databinding.FragmentIngredientBinding
import com.example.android.bakingapp.network.Ingredient

class IngredientFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as MainActivity).title = "Ingredients"

        val ingredients = arguments?.getParcelableArrayList<Ingredient>("Ingredients") as ArrayList<Ingredient>
        val ingredientAdapter =
            IngredientsAdapter(ingredients)

        ingredientAdapter.updateList(ingredients)

        val binding = FragmentIngredientBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.ingredientTab.adapter = ingredientAdapter

        return  binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Ingredients"
    }
}