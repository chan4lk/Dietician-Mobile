package com.dietician.local.mapper

import com.dietician.data.model.ProfileData
import com.dietician.local.model.ProfileLocal
import javax.inject.Inject

class ProfileDataLocalMapper @Inject constructor() : Mapper<ProfileData, ProfileLocal> {
    override fun from(e: ProfileLocal): ProfileData {
        return ProfileData(
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

    override fun to(t: ProfileData, userId: Long): ProfileLocal {
        return ProfileLocal(
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