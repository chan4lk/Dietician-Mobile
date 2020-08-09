package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dietician.domain.usecases.SaveProfileTask
import com.dietician.presentation.mapper.ProfileEntityMapper
import com.dietician.presentation.model.Profile
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val saveProfileTask: SaveProfileTask,
    private val profileMapper: ProfileEntityMapper
) : ViewModel() {
    private val _saved = MutableLiveData<Boolean>().apply {
        value = false
    }
    val saved: LiveData<Boolean> = _saved
    private val disposables = CompositeDisposable()

    fun save(profile: Profile) {
        disposables.add(saveProfileTask.buildUseCase(profileMapper.from(profile))
            .map {
                _saved.postValue(it > 0)
                it
            }.subscribe()
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}