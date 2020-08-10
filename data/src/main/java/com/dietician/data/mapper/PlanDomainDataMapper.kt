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
            target = e.target,
            userId = e.userId,
            activityLevel = e.activityLevel,
            goal = e.goal,
            pace = e.pace
        )
    }

    override fun to(t: PlanEntity): PlanData {
        return PlanData(
            id = t.id,
            name = t.name,
            duration = t.duration,
            startDate = t.startDate,
            status = t.status,
            target = t.target,
            userId = t.userId,
            activityLevel = t.activityLevel,
            goal = t.goal,
            pace = t.pace
        )
    }

    override fun from(e: PlanData, userId: Long): PlanEntity {
        return PlanEntity(
            id = e.id,
            name = e.name,
            duration = e.duration,
            startDate = e.startDate,
            status = e.status,
            target = e.target,
            userId = userId,
            activityLevel = e.activityLevel,
            goal = e.goal,
            pace = e.pace
        )
    }

    override fun to(t: PlanEntity, userId: Long): PlanData {
        return PlanData(
            id = t.id,
            name = t.name,
            duration = t.duration,
            startDate = t.startDate,
            status = t.status,
            target = t.target,
            userId = userId,
            activityLevel = t.activityLevel,
            goal = t.goal,
            pace = t.pace
        )
    }

}