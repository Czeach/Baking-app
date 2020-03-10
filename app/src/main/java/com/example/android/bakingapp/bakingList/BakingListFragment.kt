package com.example.android.bakingapp.bakingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.bakingapp.databinding.FragmentBakingListBinding

class BakingListFragment : Fragment() {

    private val viewModel: BakingListViewModel by lazy {
        ViewModelProviders.of(this).get(BakingListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentBakingListBinding.inflate(inflater)

        binding.viewModel = viewModel

        binding.setLifecycleOwner(this)

        return binding.root
    }
}
