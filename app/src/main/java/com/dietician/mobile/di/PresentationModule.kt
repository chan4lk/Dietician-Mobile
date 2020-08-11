package com.dietician.mobile.di

import com.dietician.domain.entities.*
import com.dietician.presentation.mapper.*
import com.dietician.presentation.model.*
import dagger.Binds
import dagger.Module

@Module
abstract class PresentationModule {
    @Binds
    abstract fun bindTokenMapper(
        tokenEntityMapper: TokenEntityMapper
    ): Mapper<UserTokenEntity, Token>

    @Binds
    abstract fun bindPlanMapper(
        planEntityMapper: PlanEntityMapper
    ): Mapper<PlanEntity, Plan>


    @Binds
    abstract fun bindProfileMapper(
        profileEntityMapper: ProfileEntityMapper
    ): Mapper<ProfileEntity, Profile>

    @Binds
    abstract fun bindDietMapper(
        dietEntityMapper: DietEntityMapper
    ): Mapper<DietEntity, Diet>

    @Binds
    abstract fun bindProgressMapper(
        progressEntityMapper: ProgressEntityMapper
    ): Mapper<ProgressEntity, Progress>

}