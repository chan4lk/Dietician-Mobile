package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dietician.domain.usecases.SignUpTask
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val signUpTask: SignUpTask
) : ViewModel() {
    private val _signedUp = MutableLiveData<Boolean>().apply {
        value = false
    }
    val signedUp: LiveData<Boolean> = _signedUp
    private val disposables = CompositeDisposable()

    fun signup(user: User) {
        val params = SignUpTask.Params(user.email, user.password, user.firstName, user.lastName)
        disposables.add(
            signUpTask.buildUseCase(params)
                .map {
                    _signedUp.postValue(it > 0)
                    it
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