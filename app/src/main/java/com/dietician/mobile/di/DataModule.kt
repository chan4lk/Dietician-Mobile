package com.dietician.mobile.di

import com.dietician.data.mapper.Mapper
import com.dietician.data.mapper.PlanDomainDataMapper
import com.dietician.data.mapper.TokenDomainDataMapper
import com.dietician.data.model.PlanData
import com.dietician.data.model.TokenData
import com.dietician.data.repository.DietRepositoryImpl
import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.entities.TokenEntity
import com.dietician.domain.repository.DietRepository
import dagger.Binds
import dagger.Module

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
}