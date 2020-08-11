package com.dietician.remote.mapper

import com.dietician.data.model.ProgressData
import com.dietician.remote.model.Progress
import javax.inject.Inject

class ProgressMapper @Inject constructor() : Mapper<ProgressData, Progress> {
    override fun from(e: Progress): ProgressData {
        return ProgressData(
            id = e.id,
            userId = e.userId,
            weight = e.weight,
            date = e.date
        )
    }

    override fun to(t: ProgressData): Progress {
        return Progress(
            id = t.id,
            userId = t.userId,
            weight = t.weight,
            date = t.date
        )
    }

}