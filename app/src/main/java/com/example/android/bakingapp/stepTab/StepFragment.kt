package com.example.android.bakingapp.stepTab


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.android.bakingapp.MainActivity

import com.example.android.bakingapp.databinding.FragmentStepBinding
import com.example.android.bakingapp.network.Recipe
import com.example.android.bakingapp.network.Step
import com.example.android.bakingapp.tabLayout.TabFragmentDirections

/**
 * A simple [Fragment] subclass.
 */
class StepFragment : Fragment() {
    lateinit var recipe: Recipe

private val onStepItemClicked by lazy {
    object: StepItemClickListener {
        override fun invoke(step: Step) {
            val argument =
                TabFragmentDirections.actionTabFragmentToVideoFragment(
                    step
                )
            findNavController().navigate(argument)
        }
    }
}

    private var stepAdapter =
        StepAdapter(arrayListOf(), onStepItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentStepBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val steps = arguments?.getParcelableArrayList<Step>("Steps") as ArrayList<Step>

        binding.stepTab.adapter = stepAdapter

        stepAdapter.updateList(steps)

        (requireActivity() as MainActivity).title = "Steps"

        return  binding.root
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).title = "Steps"
    }


}
