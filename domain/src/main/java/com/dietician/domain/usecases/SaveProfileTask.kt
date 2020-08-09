package com.dietician.domain.usecases

import com.dietician.domain.entities.ProfileEntity
import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class SaveProfileTask @Inject constructor(
    private val repository: DietRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<Long, ProfileEntity>(backgroundScheduler, foregroundScheduler) {
    override fun generateObservable(input: ProfileEntity?): Observable<Long> {
        if (input == null) {
            throw IllegalArgumentException("profile info cannot be null")
        }
        return repository.saveProfile(input)
    }

}