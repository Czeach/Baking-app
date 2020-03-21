package com.example.android.bakingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bakingapp.databinding.HomeListBinding
import com.example.android.bakingapp.databinding.IngredientListBinding
import com.example.android.bakingapp.network.Ingredient
import com.example.android.bakingapp.network.Recipe
import com.example.android.bakingapp.tabs.DescriptionFragment
import com.example.android.bakingapp.tabs.IngredientFragment
import com.example.android.bakingapp.tabs.VideosFragment
import kotlinx.android.synthetic.main.home_list.view.*
import kotlinx.android.synthetic.main.ingredient_list.view.*

typealias bakingItemClickListener = (Recipe) -> Unit

class BakingListAdapter(private var list: ArrayList<Recipe>, private val clickListener: bakingItemClickListener) :
    RecyclerView.Adapter<BakingListAdapter.BakingListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BakingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return BakingListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BakingListViewHolder, position: Int) {
        val recipe: Recipe = list[position]
        holder.bind(recipe)
    }

    fun updateList(recipeList: ArrayList<Recipe>) {
        list = recipeList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size


    inner class BakingListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.home_list, parent, false)),
        View.OnClickListener {

        private val binding = HomeListBinding.inflate(inflater)

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
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            var recipe = list[adapterPosition]
            clickListener.invoke(recipe)
        }

        fun bind(recipe: Recipe) {
            binding.viewModel = recipe

            mNameView?.text = recipe.name
            mServingsView?.text = recipe.servings.toString()

            binding.executePendingBindings()
        }

    }
}


class TabsAdapter(fm: FragmentManager, private var totalTabs: Int) :
        FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return IngredientFragment()
            }
            1 -> {
                return DescriptionFragment()
            }
            else -> {
                return VideosFragment()
            }
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}


class IngredientsTabAdapter(private var list: ArrayList<Ingredient> ):
RecyclerView.Adapter<IngredientsTabAdapter.IngredientsTabViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsTabViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return IngredientsTabViewHolder(inflater, parent )
    }

    override fun onBindViewHolder(holder: IngredientsTabViewHolder, position: Int) {
        val ingredient: Ingredient = list[position]

        holder.Bind(ingredient)
    }

    fun updateList(ingredientList: ArrayList<Ingredient>) {
        list = ingredientList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class IngredientsTabViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.ingredient_list, parent, false)) {

        private val binding = IngredientListBinding.inflate(inflater)

        private var mIngredient: TextView? = null

        init {
            binding.apply {
                invalidateAll()

                mIngredient = itemView.ingredient_view
            }
        }

        fun Bind(ingredient: Ingredient) {
            binding.viewModel = ingredient

            mIngredient?.text = ingredient.ingredient

            binding.executePendingBindings()
        }
    }

}