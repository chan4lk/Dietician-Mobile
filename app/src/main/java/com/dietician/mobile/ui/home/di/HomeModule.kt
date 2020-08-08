package com.dietician.mobile.ui.home.di

import androidx.lifecycle.ViewModel
import com.dietician.mobile.di.ViewModelKey
import com.dietician.presentation.viewmodels.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindViewModel(viewModel: HomeViewModel): ViewModel
}