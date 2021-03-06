package com.dietician.mobile.ui.progress.di

import androidx.lifecycle.ViewModel
import com.dietician.mobile.di.ViewModelKey
import com.dietician.presentation.viewmodels.ProgressViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ProgressModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProgressViewModel::class)
    abstract fun bindViewModel(viewMode: ProgressViewModel): ViewModel
}