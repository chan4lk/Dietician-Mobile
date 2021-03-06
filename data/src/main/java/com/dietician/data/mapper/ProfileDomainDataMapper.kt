package com.dietician.data.mapper

import com.dietician.data.model.ProfileData
import com.dietician.domain.entities.ProfileEntity
import javax.inject.Inject

class ProfileDomainDataMapper @Inject constructor() : Mapper<ProfileEntity, ProfileData> {
    override fun from(e: ProfileData): ProfileEntity {
        return ProfileEntity(
            id = e.id,
            age = e.age,
            height = e.height,
            weight = e.weight,
            gender = e.gender,
            isPregnant = e.isPregnant,
            isVegetarian = e.isVegetarian,
            userId = e.userId
        )

    }

    override fun to(t: ProfileEntity): ProfileData {
        return ProfileData(
            id = t.id,
            age = t.age,
            height = t.height,
            weight = t.weight,
            gender = t.gender,
            isPregnant = t.isPregnant,
            isVegetarian = t.isVegetarian,
            userId = t.userId
        )
    }

    override fun from(e: ProfileData, userId: Long): ProfileEntity {
        return ProfileEntity(
            id = e.id,
            age = e.age,
            height = e.height,
            weight = e.weight,
            gender = e.gender,
            isPregnant = e.isPregnant,
            isVegetarian = e.isVegetarian,
            userId = userId
        )
    }

    override fun to(t: ProfileEntity, userId: Long): ProfileData {
        return ProfileData(
            id = t.id,
            age = t.age,
            height = t.height,
            weight = t.weight,
            gender = t.gender,
            isPregnant = t.isPregnant,
            isVegetarian = t.isVegetarian,
            userId = userId
        )
    }

}