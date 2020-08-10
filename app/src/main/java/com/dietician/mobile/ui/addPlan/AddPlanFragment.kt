package com.dietician.mobile.ui.addPlan

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.mobile.utils.formatToServerDateDefaults
import com.dietician.presentation.model.Plan
import com.dietician.presentation.model.Status
import com.dietician.presentation.viewmodels.AddPlanViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class AddPlanFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val addPlanViewModel by viewModels<AddPlanViewModel> { viewModelFactory }

    private var selectedPacePosition = -1

    private var selectedActivityLevelPosition = -1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .addPlanComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root = inflater.inflate(R.layout.fragment_add_plan, container, false)
        val goalSwitch: SwitchMaterial = root.findViewById(R.id.goal_switch)
        val activityLevelDropdown: AutoCompleteTextView = root.findViewById(R.id.filled_exposed_dropdown)
        val paceLevelDropdown: AutoCompleteTextView = root.findViewById(R.id.pace_filled_exposed_dropdown)
        val targetTextWrapper: TextInputLayout = root.findViewById(R.id.target_weight_text_wrapper)
        val paceTextWrapper: TextInputLayout = root.findViewById(R.id.pace_textInputLayout)
        val targetText: TextInputEditText = root.findViewById(R.id.target_weight)
        val nameText: TextInputEditText = root.findViewById(R.id.plan_name_text)
        val slider: Slider = root.findViewById(R.id.duration_slider)
        val saveButton: MaterialButton = root.findViewById(R.id.plan_save_btn)
        val loader: ProgressBar = root.findViewById(R.id.loading)

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
                    selectedPacePosition = position
                    Timber.d(item)
                    slider.isVisible = item == res.getString(R.string.pace_level_3)
                }
            activityLevelDropdown.onItemClickListener =
                AdapterView.OnItemClickListener{_, _, position, _ ->
                    selectedActivityLevelPosition = position
                }

            goalSwitch.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    false -> {
                        paceLevelDropdown.isVisible = false
                        targetTextWrapper.isVisible = false
                        paceTextWrapper.isVisible = false
                        targetText.isVisible = false
                        slider.isVisible = false
                        goalSwitch.text = res.getString(R.string.goal_hint_maintain)
                    }
                    else -> {
                        paceLevelDropdown.isVisible = true
                        targetTextWrapper.isVisible = true
                        paceTextWrapper.isVisible = true
                        targetText.isVisible = true
                        goalSwitch.text = res.getString(R.string.goal_hint_change)
                        val position = selectedPacePosition
                        if (position != -1) {
                            slider.isVisible = position == 2
                        } else {
                            paceLevelDropdown.listSelection = 0
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
            addPlanViewModel.save(
                Plan(
                    id = 0,
                    userId = 0,
                    activityLevel = selectedActivityLevelPosition,
                    goal = when (goalSwitch.isEnabled) {
                        true -> 0 //change weight
                        else -> 1 // maintain weight
                    },
                    pace = when (goalSwitch.isEnabled) {
                        true -> selectedPacePosition
                        else -> 4 //value which is not in dropdown
                    },
                    name = nameText.text.toString(),
                    duration = slider.value.toLong(),
                    startDate = Date().formatToServerDateDefaults(),
                    status = 0,
                    target = when (targetText.text.toString() != "") {
                        true -> targetText.text.toString().toLong()
                        else -> 0
                    }
                )
            )
        }

        addPlanViewModel.source.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loader.isVisible = true
                }
                Status.ERROR -> {
                    loader.isVisible = false
                }
                Status.SUCCESS -> {
                    loader.isVisible = false
                    findNavController().navigate(R.id.nav_plan)
                }
            }
        })


        return root
    }
}
