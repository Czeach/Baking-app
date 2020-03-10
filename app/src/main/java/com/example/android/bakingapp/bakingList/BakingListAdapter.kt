package com.example.android.bakingapp.bakingList

import androidx.recyclerview.widget.RecyclerView
import com.example.android.bakingapp.ListItemViewHolder

class BakingListAdapter: RecyclerView.Adapter<ListItemViewHolder>() {
    var data = listOf<BakingList>()
}