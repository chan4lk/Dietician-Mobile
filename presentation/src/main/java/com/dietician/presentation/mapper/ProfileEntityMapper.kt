package com.dietician.presentation.mapper

import com.dietician.domain.entities.ProfileEntity
import com.dietician.presentation.model.Profile
import javax.inject.Inject

class ProfileEntityMapper @Inject constructor() : Mapper<ProfileEntity, Profile> {
    override fun from(e: Profile): ProfileEntity {
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

    override fun to(t: ProfileEntity): Profile {
        return Profile(
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

}