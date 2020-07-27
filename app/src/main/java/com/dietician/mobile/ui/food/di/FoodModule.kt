package com.dietician.mobile.ui.food.di

import androidx.lifecycle.ViewModel
import com.dietician.mobile.di.ViewModelKey
import com.dietician.mobile.ui.food.FoodViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class FoodModule {
    @Binds
    @IntoMap
    @ViewModelKey(FoodViewModel::class)
    abstract fun bindViewModel(viewModel: FoodViewModel):ViewModel
}