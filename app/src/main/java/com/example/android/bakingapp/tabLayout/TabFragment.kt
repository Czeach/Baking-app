package com.example.android.bakingapp.tabLayout


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.navArgs
import com.example.android.bakingapp.R
import com.example.android.bakingapp.ingredientTab.IngredientFragment
import com.example.android.bakingapp.stepTab.StepFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_tab.*

/**
 * A simple [Fragment] subclass.
 */
class TabFragment : Fragment() {

    var tab: ImageView? = null
    private lateinit var tabAdapter: TabAdapter
    val args: TabFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabAdapter = TabAdapter(this, CreateFragments())
        view_pager.adapter = tabAdapter

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            when(position) {
                0 -> tab.text = "Ingredients"
                1 -> tab.text = "Steps"
            }
        }.attach()
    }

    private fun CreateFragments(): ArrayList<Fragment> {
        val fragments: ArrayList<Fragment> = arrayListOf()

        fragments.add(IngredientFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("Ingredients", args.recipe.ingredients)
            }
        })

        fragments.add(StepFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList("Steps", args.recipe.steps)
            }
        })


        return fragments
    }
}
