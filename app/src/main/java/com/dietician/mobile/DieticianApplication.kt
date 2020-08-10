package com.dietician.mobile

import android.app.Application
import com.dietician.mobile.di.AppComponent
import com.dietician.mobile.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree


open class DieticianApplication : Application() {
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
        return DaggerAppComponent.factory().create(applicationContext)
    }

}