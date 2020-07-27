package com.dietician.mobile.ui.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dietician.data.model.Token
import com.dietician.data.repository.RemoteDataSource
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : ViewModel() {
    private val _loggedIn = MutableLiveData<Boolean>().apply {
        value = false
    }
    val loggedIn: LiveData<Boolean> = _loggedIn
    private val disposables = CompositeDisposable()
    fun login(userName: String, password: String) {
        disposables.add(remoteDataSource.login(userName, password)
            .subscribeOn(Schedulers.io())
            .onErrorReturn { Token("") }
            .subscribe(Consumer {
                if (!TextUtils.isEmpty(it.token)) {
                    _loggedIn.postValue(true)
                    println(it.token)
                }
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }

}