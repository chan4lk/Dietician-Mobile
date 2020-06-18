package com.dietician.mobile.ui.addPlan.di

import androidx.lifecycle.ViewModel
import com.dietician.mobile.di.ViewModelKey
import com.dietician.mobile.ui.addPlan.AddPlanViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AddPlanModule {
    @Binds
    @IntoMap
    @ViewModelKey(AddPlanViewModel::class)
    abstract fun bindViewModel(viewModel: AddPlanViewModel): ViewModel
}