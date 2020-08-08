package com.dietician.remote.mapper

import com.dietician.data.model.PlanData
import com.dietician.remote.model.PlanWrapper
import javax.inject.Inject

class ResponseMapper @Inject constructor() {
    fun mapToPlan(plan: PlanWrapper): PlanData {
        return PlanData(
            name = plan.name,
            target = plan.target,
            status = plan.status,
            startDate = plan.startDate,
            duration = plan.duration,
            id = plan.id
        )
    }
}