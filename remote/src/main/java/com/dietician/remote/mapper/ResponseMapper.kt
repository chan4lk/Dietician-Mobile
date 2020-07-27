package com.dietician.remote.mapper

import com.dietician.data.model.Plan
import com.dietician.remote.model.PlanWrapper
import javax.inject.Inject

class ResponseMapper @Inject constructor() {
    fun mapToPlan(plan: PlanWrapper): Plan {
        return Plan(plan.name)
    }
}