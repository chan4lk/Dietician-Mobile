package com.dietician.mobile.ui.addPlan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber
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
        val goalSwitch: SwitchMaterial = root.findViewById(R.id.goal_switch)
        val activityLevelDropdown: AutoCompleteTextView =
            root.findViewById(R.id.filled_exposed_dropdown)
        val paceLevelDropdown: AutoCompleteTextView =
            root.findViewById(R.id.pace_filled_exposed_dropdown)
        val targetTextWrapper: TextInputLayout = root.findViewById(R.id.target_weight_text_wrapper)
        val paceTextWrapper: TextInputLayout = root.findViewById(R.id.pace_textInputLayout)
        val targetText: TextInputEditText = root.findViewById(R.id.target_weight)
        val slider: Slider = root.findViewById(R.id.duration_slider)
        val saveButton: MaterialButton = root.findViewById(R.id.plan_save_btn)

        activity?.resources?.let { res ->

            val activityLevels = res.getStringArray(R.array.activity_levels)
            val adapter = ArrayAdapter(
                requireContext(),
                R.layout.activity_dropdown_menu_popup_item,
                activityLevels
            )
            activityLevelDropdown.setAdapter(adapter)

            val paceLevels = res.getStringArray(R.array.pace_levels)
            val paceAdapter = ArrayAdapter(
                requireContext(),
                R.layout.activity_dropdown_menu_popup_item,
                paceLevels
            )
            paceLevelDropdown.setAdapter(paceAdapter)

            paceLevelDropdown.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    val item = paceAdapter.getItem(position)
                    Timber.d(item)
                    slider.isVisible = item == res.getString(R.string.pace_level_3)
                }

            goalSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    false -> {
                        paceLevelDropdown.isVisible = false
                        targetTextWrapper.isVisible = false
                        paceTextWrapper.isVisible = false
                        targetText.isVisible = false
                        slider.isVisible = false
                        goalSwitch.text = res.getString(R.string.goal_hint_change)
                    }
                    else -> {
                        paceLevelDropdown.isVisible = true
                        targetTextWrapper.isVisible = true
                        paceTextWrapper.isVisible = true
                        targetText.isVisible = true
                        goalSwitch.text = res.getString(R.string.goal_hint_maintain)
                        val position = paceLevelDropdown.listSelection
                        if (position != -1) {
                            slider.isVisible = position == 3
                        } else {
                            paceLevelDropdown.listSelection = 1
                        }
                    }
                }
            }

            // Hide All
            paceLevelDropdown.isVisible = false
            targetTextWrapper.isVisible = false
            paceTextWrapper.isVisible = false
            targetText.isVisible = false
            slider.isVisible = false


        }




        saveButton.setOnClickListener {

        }


        return root
    }
}
