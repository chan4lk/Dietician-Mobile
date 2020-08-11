package com.dietician.domain.usecases

import com.dietician.domain.entities.ProgressEntity
import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class SaveProgressTask @Inject constructor(
    private val repository: DietRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<Long, ProgressEntity>(backgroundScheduler, foregroundScheduler) {
    override fun generateObservable(input: ProgressEntity?): Observable<Long> {
        if (input == null) {
            throw IllegalArgumentException("progress info cannot be null")
        }
        return repository.saveProgress(input)
    }

}