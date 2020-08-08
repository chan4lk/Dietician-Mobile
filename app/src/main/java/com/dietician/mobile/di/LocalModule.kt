package com.dietician.mobile.di

import android.content.Context
import com.dietician.data.model.PlanData
import com.dietician.data.model.TokenData
import com.dietician.data.repository.LocalDataSource
import com.dietician.local.database.DietDB
import com.dietician.local.mapper.Mapper
import com.dietician.local.mapper.PlanDataLocalMapper
import com.dietician.local.mapper.TokenDataLocalMapper
import com.dietician.local.model.PlanLocal
import com.dietician.local.model.TokenLocal
import com.dietician.local.source.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [LocalModule.Binders::class])
class LocalModule {
    @Module
    interface Binders {
        @Binds
        fun bindLocalDataSource(
            localDataSourceImpl: LocalDataSourceImpl
        ): LocalDataSource

        @Binds
        fun bindTokenMapper(
            tokenDataLocalMapper: TokenDataLocalMapper
        ): Mapper<TokenData, TokenLocal>

        @Binds
        fun bindPlanMapper(
            planDataLocalMapper: PlanDataLocalMapper
        ): Mapper<PlanData, PlanLocal>
    }

    @Provides
    @Singleton
    fun providesDatabase(
        context: Context
    ) = DietDB.getInstance(context)

    @Provides
    @Singleton
    fun providesTokenDAO(
        dietDB: DietDB
    ) = dietDB.getTokenDao()

    @Provides
    @Singleton
    fun providesPlanDAO(
        dietDB: DietDB
    ) = dietDB.getPlanDao()
}