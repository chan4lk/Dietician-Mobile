package com.dietician.mobile.ui.profile.di

import com.dietician.mobile.ui.profile.ProfileFragment
import dagger.Subcomponent

@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): ProfileComponent
    }

    fun inject(profileFragment: ProfileFragment)
}