package com.dietician.mobile.ui.signup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.presentation.model.User
import com.dietician.presentation.viewmodels.SignUpViewModel
import javax.inject.Inject

class SignUpFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<SignUpViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .signUpComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_sign_up, container, false)
        val userName: EditText = root.findViewById(R.id.username)
        val password: EditText = root.findViewById(R.id.password)
        val firstName: EditText = root.findViewById(R.id.firstname)
        val lastName: EditText = root.findViewById(R.id.lastname)
        val signupButton: Button = root.findViewById(R.id.signup_btn)

        signupButton.setOnClickListener {
            viewModel.signup(
                User(
                    userName.text.toString(),
                    password.text.toString(),
                    firstName.text.toString(),
                    lastName.text.toString()
                )
            )
        }

        viewModel.signedUp.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> findNavController().navigate(R.id.nav_login)
            }

        })

        return root
    }
}
