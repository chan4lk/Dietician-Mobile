package com.dietician.remote.mapper

import com.dietician.data.model.Plan
import com.dietician.remote.model.PlanWrapper

class ResponseMapper {
    fun mapToPlan(plan: PlanWrapper): Plan {
        return Plan(plan.name)
    }
}