package com.dietician.mobile.di

import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.entities.ProfileEntity
import com.dietician.domain.entities.UserTokenEntity
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.mapper.PlanEntityMapper
import com.dietician.presentation.mapper.ProfileEntityMapper
import com.dietician.presentation.mapper.TokenEntityMapper
import com.dietician.presentation.model.Plan
import com.dietician.presentation.model.Profile
import com.dietician.presentation.model.Token
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

}