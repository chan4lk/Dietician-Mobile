package com.dietician.mobile.ui.login


import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.presentation.model.Status
import com.dietician.presentation.viewmodels.LoginViewModel
import com.google.android.material.button.MaterialButton
import javax.inject.Inject


class LoginFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<LoginViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .loginComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_login, container, false)
        val userName: TextView = root.findViewById(R.id.username_text)
        val password: TextView = root.findViewById(R.id.password_text)
        val loginButton: MaterialButton = root.findViewById(R.id.login_btn)
        val signUpButton: MaterialButton = root.findViewById(R.id.nav_signup_btn)
        val loader: ProgressBar = root.findViewById(R.id.loading)

        loginButton.setOnClickListener {
            viewModel.login(userName.text.toString(), password.text.toString())
        }

        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.nav_signup)
        }

        viewModel.source.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loader.isVisible = false
                    if (!TextUtils.isEmpty(it.data?.token)) {
                        findNavController().navigate(R.id.nav_home)
                    }
                }
                Status.ERROR -> {
                    loader.isVisible = false
                    loginButton.isEnabled = true
                }

                Status.LOADING -> {
                    loader.isVisible = true
                    loginButton.isEnabled = false
                }
            }

        })

        return root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}
