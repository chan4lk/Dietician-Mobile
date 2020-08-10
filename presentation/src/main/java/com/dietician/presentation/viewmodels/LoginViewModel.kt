package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.entities.UserTokenEntity
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.LoginTask
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import com.dietician.presentation.model.Token
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginTask: LoginTask,
    private val tokenMapper: Mapper<UserTokenEntity, Token>,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val loginMediator = MediatorLiveData<Resource<Token>>()

    val source: LiveData<Resource<Token>>
        get() = loginMediator

    private val disposables = CompositeDisposable()
    fun login(userName: String, password: String) {
        val params = LoginTask.Params(userName, password)
        val resource =
            loginTask.buildUseCase(params)
                .map {
                    tokenRepository.setToken(it)
                    tokenMapper.to(it)
                }
                .map { Resource.success(it) }
                .startWith(Resource.loading())
                .onErrorResumeNext(
                    Function {
                        Observable.just(Resource.error(it.localizedMessage!!))
                    }
                ).toFlowable(BackpressureStrategy.LATEST)
                .toLiveData()
        loginMediator.addSource(resource) {
            loginMediator.value = it
            if (it.status != Status.LOADING) {
                loginMediator.removeSource(resource)
            }
        }

    }


    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}