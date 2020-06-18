package com.dietician.mobile.ui.addPlan.di

import com.dietician.mobile.ui.addPlan.AddPlanFragment
import dagger.Subcomponent

@Subcomponent(modules = [AddPlanModule::class])
interface AddPlanComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AddPlanComponent
    }

    fun inject(addPlanFragment: AddPlanFragment)
}