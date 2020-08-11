package com.dietician.data.mapper

import com.dietician.data.model.ProgressData
import com.dietician.domain.entities.ProgressEntity
import javax.inject.Inject

class ProgressDomainDataMapper @Inject constructor() : Mapper<ProgressEntity, ProgressData> {
    override fun from(e: ProgressData): ProgressEntity {
        return ProgressEntity(
            id = e.id,
            date = e.date,
            userId = e.userId,
            weight = e.weight
        )
    }

    override fun to(t: ProgressEntity): ProgressData {
        return ProgressData(
            id = t.id,
            date = t.date,
            userId = t.userId,
            weight = t.weight
        )
    }

    override fun from(e: ProgressData, userId: Long): ProgressEntity {
        return ProgressEntity(
            id = e.id,
            date = e.date,
            userId = userId,
            weight = e.weight
        )
    }

    override fun to(t: ProgressEntity, userId: Long): ProgressData {
        return ProgressData(
            id = t.id,
            date = t.date,
            userId = userId,
            weight = t.weight
        )
    }

}