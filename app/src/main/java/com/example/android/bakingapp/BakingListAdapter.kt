package com.example.android.bakingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bakingapp.databinding.HomeListBinding
import com.example.android.bakingapp.databinding.IngredientListBinding
import com.example.android.bakingapp.network.Ingredient
import com.example.android.bakingapp.network.Recipe
import kotlinx.android.synthetic.main.home_list.view.*
import kotlinx.android.synthetic.main.ingredient_list.view.*


class BakingListAdapter(private var list: ArrayList<Recipe>, val onClickListener: OnClickListener) :
    ListAdapter<Recipe, BakingListAdapter.BakingListViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.steps == newItem.steps
        }
    }

    class OnClickListener(val clickListener: (recipe: Recipe) -> Unit) {
        fun onClick(recipe: Recipe) = clickListener(recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BakingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return BakingListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BakingListViewHolder, position: Int) {
        var recipe: Recipe = list[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(recipe)
        }
        holder.bind(recipe)
    }

    fun updateList(recipeList: ArrayList<Recipe>) {
        list = recipeList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size


    class BakingListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.home_list, parent, false)) {

        val binding = HomeListBinding.inflate(inflater)

        private var mImageView: ImageView? = null
        private var mNameView: TextView? = null
        private var mServingsView: TextView? = null

        init {
            binding.apply {
                invalidateAll()

                mImageView = itemView.image_view
                mNameView = itemView.name_view
                mServingsView = itemView.servings_view

            }
        }

        fun bind(recipe: Recipe) {
            binding.viewModel = recipe

            mNameView?.text = recipe.name
            mServingsView?.text = recipe.servings.toString()

            binding.executePendingBindings()
        }
    }
}


//class IngredientAdapter(private var list: ArrayList<Recipe>) :
//    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {
//
//    class IngredientViewHolder(inflater: LayoutInflater, parent: ViewGroup):
//        RecyclerView.ViewHolder(inflater.inflate(R.layout.ingredient_list, parent, false)) {
//
//        val binding = IngredientListBinding.inflate(inflater)
//
//        private var mIngredientView: TextView? = null
//
//        init {
//            binding.apply {
//                invalidateAll()
//
//                mIngredientView = itemView.ingredient_view
//            }
//        }
//
//        fun bind(recipe: Recipe){
//            binding.viewModel = recipe
//
//            mIngredientView?.text = recipe.ingredients.toString()
//
//            binding.executePendingBindings()
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
//        val inflater = LayoutInflater.from(parent.context)
//
//        return IngredientViewHolder(inflater, parent)
//    }
//
//    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
//        var recipe: Recipe = list[position]
//
//        holder.bind(recipe)
//    }
//
//    fun updateIngredientList(recipeList: ArrayList<Recipe>) {
//        list = recipeList
//        notifyDataSetChanged()
//    }
//
//    override fun getItemCount(): Int = list.size
//}

