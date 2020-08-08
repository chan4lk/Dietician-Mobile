package com.dietician.data.mapper

import com.dietician.data.model.PlanData
import com.dietician.domain.entities.PlanEntity
import javax.inject.Inject

class PlanDomainDataMapper @Inject constructor() : Mapper<PlanEntity, PlanData> {
    override fun from(e: PlanData): PlanEntity {
        return PlanEntity(
            id = e.id,
            name = e.name,
            duration = e.duration,
            startDate = e.startDate,
            status = e.status,
            target = e.target
        )
    }

    override fun to(t: PlanEntity): PlanData {
        return PlanData(
            id = t.id,
            name = t.name,
            duration = t.duration,
            startDate = t.startDate,
            status = t.status,
            target = t.target
        )
    }

}