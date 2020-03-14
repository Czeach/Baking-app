package com.example.android.bakingapp.bakingList

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bakingapp.R
import com.example.android.bakingapp.databinding.HomeListBinding
import com.example.android.bakingapp.network.Recipe

class BakingListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.home_list, parent, false)) {

    val binding = HomeListBinding.inflate(inflater)

    private var mImageView: ImageView? = null
    private var mNameView: TextView? = null
    private var mServingsView: TextView? = null

    init {
        mImageView = binding.imageView
        mNameView = binding.nameView
        mServingsView = binding.servingsView
    }

    fun bind(recipe: Recipe) {
        mNameView?.text = recipe.name
        mServingsView?.text = recipe.servings.toString()
    }


}

class BakingListAdapter(private var list: ArrayList<Recipe>) : RecyclerView.Adapter<BakingListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BakingListViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return BakingListViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: BakingListViewHolder, position: Int) {
        var recipe: Recipe = list[position]
        holder.bind(recipe)
    }

    fun updateList(recipeList: ArrayList<Recipe>) {
        list = recipeList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}

