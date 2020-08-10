package com.dietician.mobile.ui.addPlan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import javax.inject.Inject


class AddPlanFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val addPlanViewModel by viewModels<AddPlanViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .addPlanComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_add_plan, container, false)

        activity?.resources?.let {
            val activityLevels = it.getStringArray(R.array.activity_levels)
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.activity_dropdown_menu_popup_item,
                activityLevels
            )

            val paceLevelDropdown: AutoCompleteTextView =
                root.findViewById(R.id.filled_exposed_dropdown)
            paceLevelDropdown.setAdapter(adapter)

            val paceLevels = it.getStringArray(R.array.pace_levels)
            val paceAdapter = ArrayAdapter(
                requireContext(),
                R.layout.activity_dropdown_menu_popup_item,
                paceLevels
            )

            val activityLevelDropdown: AutoCompleteTextView =
                root.findViewById(R.id.pace_filled_exposed_dropdown)
            activityLevelDropdown.setAdapter(paceAdapter)
        }

        return root
    }
}
