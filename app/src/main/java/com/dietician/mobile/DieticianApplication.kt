package com.dietician.mobile

import android.app.Application
import com.dietician.mobile.di.AppComponent
import com.dietician.mobile.di.DaggerAppComponent

open class DieticianApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }

}