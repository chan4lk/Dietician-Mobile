package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.usecases.SaveProfileTask
import com.dietician.presentation.mapper.ProfileEntityMapper
import com.dietician.presentation.model.Profile
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val saveProfileTask: SaveProfileTask,
    private val profileMapper: ProfileEntityMapper
) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val mediator = MediatorLiveData<Resource<Long>>()

    val source: LiveData<Resource<Long>>
        get() = mediator

    fun save(profile: Profile) {
        val resource =
            saveProfileTask
                .buildUseCase(profileMapper.from(profile))
                .map {
                    it
                }
                .map { Resource.success(it) }
                .startWith(Resource.loading())
                .onErrorResumeNext(Function {
                    Observable.just(Resource.error(it.localizedMessage!!))
                })
                .toFlowable(BackpressureStrategy.LATEST)
                .toLiveData()

        mediator.addSource(resource) {
            mediator.value = it
            if (it.status != Status.LOADING) {
                mediator.removeSource(resource)
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}