package com.dietician.mobile.ui.food.di


import com.dietician.mobile.ui.food.FoodFragment
import dagger.Subcomponent

@Subcomponent(modules = [FoodModule::class])
interface FoodComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FoodComponent
    }

    fun inject(foodFragment: FoodFragment)
}