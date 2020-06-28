package com.dietician.mobile.ui.plan.di

import com.dietician.mobile.ui.plan.PlanFragment
import dagger.Subcomponent

@Subcomponent(modules = [PlanModule::class])
interface PlanComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): PlanComponent
    }

    fun inject(planFragment: PlanFragment)
}