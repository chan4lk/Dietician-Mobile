package com.dietician.local.mapper

import com.dietician.data.model.PlanData
import com.dietician.local.model.PlanLocal
import javax.inject.Inject

class PlanDataLocalMapper @Inject constructor() : Mapper<PlanData, PlanLocal> {
    override fun from(e: PlanLocal): PlanData {
        return PlanData(
            id = e.id,
            name = e.name,
            duration = e.duration,
            startDate = e.startDate,
            status = e.status,
            target = e.target
        )
    }

    override fun to(t: PlanData, userName: String): PlanLocal {
        return PlanLocal(
            id = t.id,
            userName = userName,
            name = t.name,
            duration = t.duration,
            startDate = t.startDate,
            status = t.status,
            target = t.target
        )
    }
}