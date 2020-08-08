package com.dietician.mobile.di

import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    @Background
    fun provideBackgroundScheduler(): Scheduler {
        return Schedulers.io()
    }

    @Singleton
    @Provides
    @Foreground
    fun provideForegroundScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}