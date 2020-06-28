package com.dietician.mobile.ui.plan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.mobile.ui.plans.PlanAdapter
import kotlinx.android.synthetic.main.fragment_plan.*
import javax.inject.Inject

class PlanFragment @Inject
    constructor(): Fragment(), PlanAdapter.ClickListener {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var planAdapter: PlanAdapter

    private val planFragment by viewModels<PlanViewModel>{viewModelFactory}

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_plan, container, false)
        planAdapter.setClickListener(this)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        planAdapter.setPlanItem(plans)

        plan_list_recycler_view?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = planAdapter
        }
    }

    override fun onPlanItemClick(planItem: String) {
        //load diet menu screen of the clicked plan item
    }


}