package com.dietician.domain.usecases

import com.dietician.domain.entities.ProfileEntity
import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetProfileTask @Inject constructor(
    private val dietRepository: DietRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<ProfileEntity, Long>(
    backgroundScheduler,
    foregroundScheduler
) {
    override fun generateObservable(input: Long?): Observable<ProfileEntity> {
        if (input == null) {
            throw IllegalArgumentException("user id cannot be null")
        }

        return dietRepository.getProfile(input)
    }

}