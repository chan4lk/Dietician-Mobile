package com.dietician.domain.usecases

import com.dietician.domain.entities.ProgressEntity
import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetProgressTask @Inject constructor(
    private val dietRepository: DietRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<List<ProgressEntity>, Long>(
    backgroundScheduler,
    foregroundScheduler
) {
    override fun generateObservable(input: Long?): Observable<List<ProgressEntity>> {
        if (input == null) {
            throw IllegalArgumentException("userId cannot be null")
        }

        return dietRepository.getProgress(input)
    }

}