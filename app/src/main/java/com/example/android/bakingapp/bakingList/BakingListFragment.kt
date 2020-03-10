package com.example.android.bakingapp.bakingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.android.bakingapp.databinding.FragmentBakingListBinding

class BakingListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentBakingListBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        return binding.root
    }
}
