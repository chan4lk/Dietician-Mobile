package com.dietician.mobile.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.RadioButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.presentation.model.Gender.FEMALE
import com.dietician.presentation.model.Gender.MALE
import com.dietician.presentation.model.Profile
import com.dietician.presentation.model.Status
import com.dietician.presentation.viewmodels.ProfileViewModel
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class ProfileFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<ProfileViewModel> { viewModelFactory }

    private var id: Long = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .profileComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_health_profile, container, false)
        val age: TextInputEditText = root.findViewById(R.id.age_text)
        val weight: TextInputEditText = root.findViewById(R.id.weight_text)
        val height: TextInputEditText = root.findViewById(R.id.height_text)
        val maleGender: RadioButton = root.findViewById(R.id.male_radio_btn)
        val femaleGender: RadioButton = root.findViewById(R.id.female_radio_btn)
        val isPregnant: CheckBox = root.findViewById(R.id.isPregnant)
        val isVeg: CheckBox = root.findViewById(R.id.isVeg)
        val saveButton: MaterialButton = root.findViewById(R.id.save_profile_btn)
        val loading: ProgressBar = root.findViewById(R.id.loading)

        saveButton.setOnClickListener {
            val isMale = when (maleGender.isChecked) {
                true -> MALE.ordinal
                false -> FEMALE.ordinal
            }
            viewModel.save(
                Profile(
                    id = id,
                    userId = 0,
                    age = age.text.toString().toInt(),
                    weight = weight.text.toString().toDouble(),
                    height = height.text.toString().toDouble(),
                    isPregnant = isPregnant.isChecked,
                    isVegetarian = isVeg.isChecked,
                    gender = isMale
                )
            )
        }

        viewModel.profileSource.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.isVisible = true
                    saveButton.isEnabled = true
                }
                Status.ERROR -> {
                    loading.isVisible = false
                    saveButton.isEnabled = true
                }
                Status.SUCCESS -> {
                    loading.isVisible = false
                    saveButton.isEnabled = true

                    it.data?.let { profile ->
                        id = profile.id
                        age.setText(profile.age.toString())
                        weight.setText(profile.weight.toString())
                        height.setText(profile.height.toString())
                        isPregnant.isChecked = profile.isPregnant
                        isVeg.isChecked = profile.isVegetarian

                        when (profile.gender == MALE.ordinal) {
                            true -> maleGender.isChecked = true
                            else -> femaleGender.isChecked = true
                        }
                    }

                }
            }
        })

        viewModel.source.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.isVisible = true
                    saveButton.isEnabled = true
                }
                Status.ERROR -> {
                    loading.isVisible = false
                    saveButton.isEnabled = true
                }
                Status.SUCCESS -> {
                    loading.isVisible = false
                    saveButton.isEnabled = true
                    findNavController().navigate(R.id.nav_plan)
                }
            }
        })
        return root
    }

}