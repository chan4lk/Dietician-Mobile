package com.dietician.mobile.ui.plan.di

import androidx.lifecycle.ViewModel
import com.dietician.mobile.di.ViewModelKey
import com.dietician.mobile.ui.plan.PlanViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PlanModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlanViewModel::class)
    abstract fun bindViewModel(viewModel: PlanViewModel):ViewModel
}