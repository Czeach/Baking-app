package com.example.android.bakingapp.stepTab

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.bakingapp.R
import com.example.android.bakingapp.network.Step

typealias StepItemClickListener = (Step) -> Unit

class StepAdapter(private var list: ArrayList<Step>, var listener: StepItemClickListener):
        RecyclerView.Adapter<StepAdapter.StepViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return StepViewHolder(inflater, parent )
    }

    override fun onBindViewHolder(holder: StepViewHolder, position: Int) {
        val step: Step = list[position]

        holder.Bind(step)
    }

    fun updateList(stepList: ArrayList<Step>) {
        list = stepList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    inner class StepViewHolder(inflater: LayoutInflater, parent: ViewGroup):
            RecyclerView.ViewHolder(inflater.inflate(R.layout.step_list, parent, false)),
        View.OnClickListener {

        private var mId: TextView? = null
        private var mDescription: TextView? = null

        init {
            mId = itemView.findViewById(R.id.id_view)
            mDescription = itemView.findViewById(R.id.description_view)

            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val step = list[adapterPosition]
            listener.invoke(step)
        }

        fun Bind(step: Step) {

            mDescription?.text = step.shortDescription
            val number = 1 + step.id
            mId?.text = number.toString()
        }
    }
}