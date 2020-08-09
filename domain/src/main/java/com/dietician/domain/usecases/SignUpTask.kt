package com.dietician.domain.usecases

import com.dietician.domain.entities.UserEntity
import com.dietician.domain.qualifiers.Background
import com.dietician.domain.qualifiers.Foreground
import com.dietician.domain.repository.DietRepository
import com.dietician.domain.usecases.base.ObservableUseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class SignUpTask @Inject constructor(
    private val repository: DietRepository,
    @Background backgroundScheduler: Scheduler,
    @Foreground foregroundScheduler: Scheduler
) : ObservableUseCase<Long, SignUpTask.Params>(backgroundScheduler, foregroundScheduler) {

    data class Params(
        val email: String,
        val password: String,
        val firstName: String,
        val lastName: String
    )


    override fun generateObservable(input: Params?): Observable<Long> {
        if (input == null) {
            throw IllegalArgumentException("Sign up info cannot be null")
        }
        val userEntity = UserEntity(0, input.email, input.password, input.firstName, input.lastName)
        return repository.signUp(userEntity)
    }

}