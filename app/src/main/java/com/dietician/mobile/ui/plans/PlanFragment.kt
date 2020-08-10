package com.dietician.mobile.ui.plan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.mobile.ui.plans.PlanAdapter
import com.dietician.presentation.model.Plan
import com.dietician.presentation.model.Status
import com.dietician.presentation.viewmodels.PlanViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_plan.*
import javax.inject.Inject

class PlanFragment @Inject
    constructor(): Fragment(), PlanAdapter.ClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var planAdapter: PlanAdapter

    private val viewModel by viewModels<PlanViewModel> { viewModelFactory }

    lateinit var label: TextView
    lateinit var loader: ProgressBar

//    var plans: List<String> = emptyList()
    private val plans = listOf(
        "Plan A",
        "Plan B",
        "Plan C",
        "Plan D",
        "Plan E",
        "Plan F"
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .planComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_plan, container, false)
        label = root.findViewById(R.id.no_plan_label)
        loader = root.findViewById(R.id.loading)
        val navController = findNavController()
        val fab: FloatingActionButton = root.findViewById(R.id.fab)
        fab.setOnClickListener {
            val currentFragment = navController.currentDestination?.id
            if (currentFragment != R.id.add_plan) {
                navController.navigate(R.id.add_plan)
            }
        }

        planAdapter.setClickListener(this)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.plansListSource.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loader.isVisible = true
                }
                Status.ERROR -> {
                    loader.isVisible = false

                }
                Status.SUCCESS -> {
                    loader.isVisible = false
                    it.data?.let { plans ->
                        planAdapter.setPlanItem(plans)
                    }

                }
            }
        })


        label.visibility = View.GONE

        if (plans.count() == 0)
            label.visibility = View.VISIBLE

        plan_list_recycler_view?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = planAdapter
        }
    }

    override fun onPlanItemClick(planItem: Plan) {
        val bundle = bundleOf("planId" to planItem.id.toLong())
        findNavController().navigate(R.id.nav_today_diet, bundle)
    }

}