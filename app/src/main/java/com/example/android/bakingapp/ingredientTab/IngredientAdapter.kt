package com.example.android.bakingapp.ingredientTab

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bakingapp.R
import com.example.android.bakingapp.databinding.IngredientListBinding
import com.example.android.bakingapp.network.Ingredient
import kotlinx.android.synthetic.main.ingredient_list.view.*

class IngredientsAdapter(private var list: ArrayList<Ingredient> ):
    RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return IngredientsViewHolder(inflater, parent )
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val ingredient: Ingredient = list[position]

        holder.Bind(ingredient)
    }

    fun updateList(ingredientList: ArrayList<Ingredient>) {
        list = ingredientList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class IngredientsViewHolder(inflater: LayoutInflater, parent: ViewGroup):
        RecyclerView.ViewHolder(inflater.inflate(R.layout.ingredient_list, parent, false)) {

        private val binding = IngredientListBinding.inflate(inflater)

        private var mIngredient: TextView? = null
        private var mMeasure: TextView? = null
        private var mQuantity: TextView? = null

        init {
            binding.apply {
                invalidateAll()

                mIngredient = itemView.ingredient_view
                mMeasure = itemView.measure_view
                mQuantity = itemView.quantity_view
            }
        }

        fun Bind(ingredient: Ingredient) {
            binding.viewModel = ingredient

            mIngredient?.text = ingredient.ingredient
            mMeasure?.text = ingredient.measure
            mQuantity?.text = ingredient.quantity.toString()

            binding.executePendingBindings()
        }
    }

}