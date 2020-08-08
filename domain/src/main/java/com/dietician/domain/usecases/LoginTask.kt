package com.dietician.domain.usecases

import com.dietician.domain.entities.TokenEntity
import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject


class LoginTask @Inject constructor(
    private val repository: DietRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<TokenEntity, LoginTask.Params>(backgroundScheduler, foregroundScheduler) {

    override fun generateObservable(input: Params?): Observable<TokenEntity> {
        if (input == null) {
            throw IllegalArgumentException("Login info cannot be null")
        }
        return repository.login(input.userName, input.password)
    }

    data class Params(
        val userName: String,
        val password: String
    )

}