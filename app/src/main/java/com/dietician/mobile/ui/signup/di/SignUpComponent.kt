package com.dietician.mobile.ui.sign.di

import com.dietician.mobile.ui.signup.SignUpFragment
import com.dietician.mobile.ui.signup.di.SignUpModule
import dagger.Subcomponent

@Subcomponent(modules = [SignUpModule::class])
interface SignUpComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SignUpComponent
    }

    fun inject(loginFragment: SignUpFragment)
}