package com.example.android.bakingapp.tabs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.bakingapp.IngredientsTabAdapter
import com.example.android.bakingapp.databinding.FragmentIngredientBinding

/**
 * A simple [Fragment] subclass.
 */
class IngredientFragment : Fragment() {

    private var ingredientAdapter = IngredientsTabAdapter(arrayListOf())

    private val viewModel: IngredientViewModel by lazy {
        ViewModelProviders.of(this).get(IngredientViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_ingredient, container, false)
        val binding = FragmentIngredientBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        binding.ingredientTabRecycler.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ingredientAdapter

            binding.executePendingBindings()
        }

        viewModel.ingredient.observe(viewLifecycleOwner, Observer {
            ingredientAdapter.updateList(it)
        })

        return binding.root
    }
}
