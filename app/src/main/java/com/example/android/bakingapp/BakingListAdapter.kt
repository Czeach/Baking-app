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
