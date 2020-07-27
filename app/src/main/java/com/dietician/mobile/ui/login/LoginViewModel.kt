package com.dietician.mobile.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loggedIn = MutableLiveData<Boolean>().apply {
        value = false
    }
    val loggedIn: LiveData<Boolean> = _loggedIn

    fun login(userName: CharSequence, password: CharSequence) {
        println(userName)
        _loggedIn.value = true
    }
}