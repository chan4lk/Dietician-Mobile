package com.dietician.domain.usecases

import com.dietician.domain.entities.DietEntity
import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetDietTask @Inject constructor(
    private val dietRepository: DietRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<DietEntity, GetDietTask.Params>(
    backgroundScheduler,
    foregroundScheduler
) {
    override fun generateObservable(input: Params?): Observable<DietEntity> {
        if (input == null) {
            throw IllegalArgumentException("diet params cannot be null")
        }

        return dietRepository.getDiet(input)
    }

    data class Params(
        val userId: Long,
        val planId: Long
    )

}