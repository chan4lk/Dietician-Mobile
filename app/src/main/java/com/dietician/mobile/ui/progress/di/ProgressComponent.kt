package com.dietician.mobile.ui.progress.di

import com.dietician.mobile.ui.progress.ProgressFragment
import dagger.Subcomponent

@Subcomponent(modules = [ProgressModule::class])
interface ProgressComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): ProgressComponent
    }

    fun inject(progressFragment: ProgressFragment)
}