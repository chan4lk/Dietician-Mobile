package com.dietician.mobile.di

import com.dietician.data.mapper.*
import com.dietician.data.model.PlanData
import com.dietician.data.model.ProfileData
import com.dietician.data.model.TokenData
import com.dietician.data.model.UserData
import com.dietician.data.repository.DietRepositoryImpl
import com.dietician.data.repository.TokenRepositoryImpl
import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.entities.ProfileEntity
import com.dietician.domain.entities.TokenEntity
import com.dietician.domain.entities.UserEntity
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    abstract fun bindRepository(
        repository: DietRepositoryImpl
    ): DietRepository

    @Binds
    abstract fun bindsTokenMapper(
        mapper: TokenDomainDataMapper
    ): Mapper<TokenEntity, TokenData>

    @Binds
    abstract fun bindsPlanMapper(
        mapper: PlanDomainDataMapper
    ): Mapper<PlanEntity, PlanData>

    @Binds
    abstract fun bindsUserMapper(
        userDomainDataMapper: UserDomainDataMapper
    ): Mapper<UserEntity, UserData>

    @Binds
    abstract fun bindsProfileMapper(
        profileDomainDataMapper: ProfileDomainDataMapper
    ): Mapper<ProfileEntity, ProfileData>

    @Binds
    @Singleton
    abstract fun bindTokenRepository(
        repository: TokenRepositoryImpl
    ): TokenRepository
}