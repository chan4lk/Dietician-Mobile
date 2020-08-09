package com.dietician.mobile.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
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
        val name: TextInputEditText = root.findViewById(R.id.name_text)
        val age: TextInputEditText = root.findViewById(R.id.age_text)
        val weight: TextInputEditText = root.findViewById(R.id.weight_text)
        val height: TextInputEditText = root.findViewById(R.id.height_text)
        val gender: RadioGroup = root.findViewById(R.id.gender_radio_group)
        val isPregnant: CheckBox = root.findViewById(R.id.isPregnant)
        val isVeg: CheckBox = root.findViewById(R.id.isVeg)
        val saveButton: MaterialButton = root.findViewById(R.id.save_profile_btn)

        saveButton.setOnClickListener {
            viewModel.save(
                Profile(
                    id = 0,
                    userId = 0,
                    age = age.text.toString().toInt(),
                    weight = weight.text.toString().toDouble(),
                    height = height.text.toString().toDouble(),
                    isPregnant = isPregnant.isChecked,
                    isVegetarian = isVeg.isChecked,
                    name = name.text.toString(),
                    gender = gender.checkedRadioButtonId
                )
            )
        }

        viewModel.source.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    saveButton.isEnabled = false
                }
                Status.ERROR -> {
                    saveButton.isEnabled = true
                }
                Status.SUCCESS -> {
                    if (it.data!! > 0) {
                        findNavController().navigate(R.id.nav_plan)
                    }
                }
            }
        })
        return root
    }

}