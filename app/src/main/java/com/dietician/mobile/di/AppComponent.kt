package com.dietician.mobile.di

import android.content.Context
import com.dietician.mobile.ui.addPlan.di.AddPlanComponent
import com.dietician.mobile.ui.home.di.HomeComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilderModule::class,
        SubComponentsModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory
    fun addPlanComponent(): AddPlanComponent.Factory
}

@Module(
    subcomponents = [
        HomeComponent::class,
        AddPlanComponent::class
    ]
)
object SubComponentsModule