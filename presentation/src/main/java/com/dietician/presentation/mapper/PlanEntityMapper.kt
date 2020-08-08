package com.dietician.presentation.mapper

import com.dietician.domain.entities.PlanEntity
import com.dietician.presentation.model.Plan
import javax.inject.Inject

class PlanEntityMapper @Inject constructor() : Mapper<PlanEntity, Plan> {
    override fun from(e: Plan): PlanEntity {
        return PlanEntity(
            id = e.id,
            name = e.name,
            duration = e.duration,
            startDate = e.startDate,
            status = e.status,
            target = e.target
        )
    }

    override fun to(t: PlanEntity): Plan {
        return Plan(
            id = t.id,
            name = t.name,
            duration = t.duration,
            startDate = t.startDate,
            status = t.status,
            target = t.target
        )
    }

}