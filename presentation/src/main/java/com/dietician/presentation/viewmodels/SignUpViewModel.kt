package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.entities.UserTokenEntity
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.SignUpTask
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import com.dietician.presentation.model.Token
import com.dietician.presentation.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpTask: SignUpTask,
    private val tokenMapper: Mapper<UserTokenEntity, Token>,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val mediator = MediatorLiveData<Resource<Token>>()

    val source: LiveData<Resource<Token>>
        get() = mediator

    fun signup(user: User) {
        val params = SignUpTask.Params(user.email, user.password, user.firstName, user.lastName)

        val resource = signUpTask.buildUseCase(params)
            .map {
                tokenRepository.setToken(it)
                tokenMapper.to(it)
            }
            .map { Resource.success(it) }
            .onErrorResumeNext(
                Function {
                    Observable.just(Resource.error(it.localizedMessage!!))
                }
            ).toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()

        mediator.addSource(resource) {
            mediator.value = it
            if (it.status != Status.LOADING) {
                mediator.removeSource(resource)
            }
        }


    }

}