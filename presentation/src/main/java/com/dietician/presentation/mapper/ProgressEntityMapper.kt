package com.dietician.presentation.mapper

import com.dietician.domain.entities.ProgressEntity
import com.dietician.presentation.model.Progress
import javax.inject.Inject

class ProgressEntityMapper @Inject constructor() : Mapper<ProgressEntity, Progress> {
    override fun from(e: Progress): ProgressEntity {
        return ProgressEntity(
            id = e.id,
            userId = e.userId,
            weight = e.weight,
            date = e.date
        )
    }

    override fun to(t: ProgressEntity): Progress {
        return Progress(
            id = t.id,
            userId = t.userId,
            weight = t.weight,
            date = t.date
        )
    }

}