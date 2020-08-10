package com.dietician.remote.mapper

import com.dietician.data.model.PlanData
import com.dietician.remote.model.PlanWrapper
import javax.inject.Inject

class ResponseMapper @Inject constructor() : Mapper<PlanData, PlanWrapper> {

    override fun from(e: PlanWrapper): PlanData {
        return PlanData(
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

    override fun to(t: PlanData): PlanWrapper {
        return PlanWrapper(
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
}