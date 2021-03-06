package com.dietician.mobile.di

import android.content.Context
import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.UserTokenData
import com.dietician.data.repository.LocalDataSource
import com.dietician.local.database.DietDB
import com.dietician.local.mapper.Mapper
import com.dietician.local.mapper.PlanDataLocalMapper
import com.dietician.local.mapper.ProfileDataLocalMapper
import com.dietician.local.mapper.UserDataLocalMapper
import com.dietician.local.model.PlanLocal
import com.dietician.local.model.ProfileLocal
import com.dietician.local.model.UserLocal
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
        fun bindPlanMapper(
            planDataLocalMapper: PlanDataLocalMapper
        ): Mapper<PlanData, PlanLocal>

        @Binds
        fun bindUserMapper(
            userDataLocalMapper: UserDataLocalMapper
        ): Mapper<UserTokenData, UserLocal>

        @Binds
        fun bindProfileMapper(
            profileDataLocalMapper: ProfileDataLocalMapper
        ): Mapper<ProfileData, ProfileLocal>
    }

    @Provides
    @Singleton
    fun providesDatabase(
        context: Context
    ) = DietDB.getInstance(context)


    @Provides
    @Singleton
    fun providesPlanDAO(
        dietDB: DietDB
    ) = dietDB.getPlanDao()

    @Provides
    @Singleton
    fun providesUserDAO(
        dietDB: DietDB
    ) = dietDB.getUserDao()


    @Provides
    @Singleton
    fun providesProfileDAO(
        dietDB: DietDB
    ) = dietDB.getProfileDao()
}