package com.dietician.mobile.di

import android.content.Context
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.ui.addPlan.di.AddPlanComponent
import com.dietician.mobile.ui.food.di.FoodComponent
import com.dietician.mobile.ui.home.di.HomeComponent
import com.dietician.mobile.ui.login.di.LoginComponent
import com.dietician.mobile.ui.plan.di.PlanComponent
import com.dietician.mobile.ui.profile.di.ProfileComponent
import com.dietician.mobile.ui.progress.di.ProgressComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelBuilderModule::class,
        SubComponentsModule::class,
        RemoteModule::class,
        LocalModule::class,
        DomainModule::class,
        PresentationModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<DieticianApplication> {

    override fun inject(instance: DieticianApplication)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun homeComponent(): HomeComponent.Factory
    fun addPlanComponent(): AddPlanComponent.Factory
    fun planComponent(): PlanComponent.Factory
    fun profileComponent(): ProfileComponent.Factory
    fun progressComponent(): ProgressComponent.Factory
    fun loginComponent(): LoginComponent.Factory
    fun foodComponent(): FoodComponent.Factory

}

@Module(
    subcomponents = [
        HomeComponent::class,
        AddPlanComponent::class,
        PlanComponent::class,
        ProfileComponent::class,
        ProgressComponent::class,
        FoodComponent::class,
        LoginComponent::class
    ]
)
object SubComponentsModule