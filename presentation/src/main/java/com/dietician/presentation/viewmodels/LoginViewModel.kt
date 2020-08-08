package com.dietician.presentation.viewmodels

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.LoginTask
import com.dietician.presentation.mapper.TokenEntityMapper
import com.dietician.presentation.model.Resource
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginTask: LoginTask,
    private val tokenMapper: TokenEntityMapper,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val _loggedIn = MutableLiveData<Boolean>().apply {
        value = false
    }
    val loggedIn: LiveData<Boolean> = _loggedIn
    private val disposables = CompositeDisposable()
    fun login(userName: String, password: String) {
        val params = LoginTask.Params(userName, password)
        disposables.add(
            loginTask.buildUseCase(params)
                .map {
                    tokenRepository.setToken(it.token)
                    _loggedIn.postValue(!TextUtils.isEmpty(it.token))
                    tokenMapper.to(it)
                }
                .map { Resource.success(it) }
                .onErrorResumeNext(
                    Function {
                        Observable.just(Resource.error(it.localizedMessage!!))
                    }
                ).toFlowable(BackpressureStrategy.LATEST)
                .subscribe()
        )

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}