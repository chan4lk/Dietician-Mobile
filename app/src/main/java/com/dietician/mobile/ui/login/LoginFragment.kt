package com.dietician.mobile.ui.login


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
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

        loginButton.setOnClickListener {
            viewModel.login(userName.text.toString(), password.text.toString())
        }

        viewModel.loggedIn.observe(viewLifecycleOwner, Observer {
            when (it) {
                true -> findNavController().navigate(R.id.nav_home)
            }

        })

        return root
    }
}
