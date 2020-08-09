package com.dietician.remote.mapper

import com.dietician.data.model.ProfileData
import com.dietician.remote.model.Profile
import javax.inject.Inject

class ProfileMapper @Inject constructor() : Mapper<ProfileData, Profile> {
    override fun from(e: Profile): ProfileData {
        return ProfileData(
            id = e.id,
            name = e.name,
            age = e.age,
            height = e.height,
            weight = e.weight,
            gender = e.gender,
            isPregnant = e.isPregnant,
            isVegetarian = e.isVegetarian,
            userId = e.userId
        )
    }

    override fun to(t: ProfileData): Profile {
        return Profile(
            id = t.id,
            name = t.name,
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