package com.dietician.mobile.ui.login.di

import com.dietician.mobile.ui.login.LoginFragment
import dagger.Subcomponent

@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(loginFragment: LoginFragment)
}