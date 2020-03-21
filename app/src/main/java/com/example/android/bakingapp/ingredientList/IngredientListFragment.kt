package com.example.android.bakingapp.ingredientList

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.android.bakingapp.TabsAdapter
import com.example.android.bakingapp.bakingList.BakingListViewModel
import com.example.android.bakingapp.databinding.FragmentIngredientListBinding
import com.google.android.material.tabs.TabLayout

class IngredientListFragment : Fragment() {

    private var viewModel: BakingListViewModel? = null

//    private val viewModel: BakingListViewModel by lazy {
//        ViewModelProviders.of(this).get(BakingListViewModel::class.java)
//    }

    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(BakingListViewModel::class.java)

        // Get a reference to the binding object and inflate the fragment views.
        val binding = FragmentIngredientListBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

//        binding.ingredientListViewModel = viewModel

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        tabLayout!!.tabGravity = TabLayout.GRAVITY_FILL

        val myTabsAdapter = TabsAdapter(activity!!.supportFragmentManager, tabLayout!!.tabCount)
        viewPager!!.adapter = myTabsAdapter

        viewPager!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager!!.currentItem = tab!!.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        return binding.root
    }
}
