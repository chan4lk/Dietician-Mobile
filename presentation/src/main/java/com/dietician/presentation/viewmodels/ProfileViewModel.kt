package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.GetProfileTask
import com.dietician.domain.usecases.SaveProfileTask
import com.dietician.presentation.mapper.ProfileEntityMapper
import com.dietician.presentation.model.Profile
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val saveProfileTask: SaveProfileTask,
    getProfileTask: GetProfileTask,
    private val profileMapper: ProfileEntityMapper,
    tokenRepository: TokenRepository
) : ViewModel() {
    private val mediator = MediatorLiveData<Resource<Profile>>()
    private val mediatorSave = MediatorLiveData<Resource<Profile>>()

    val source: LiveData<Resource<Profile>>
        get() = mediatorSave

    val profileSource: LiveData<Resource<Profile>>
        get() = mediator

    init {
        val resource =
            getProfileTask
                .buildUseCase(tokenRepository.getToken().id)
                .map {
                    profileMapper.to(it)
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

    fun save(profile: Profile) {
        val resource =
            saveProfileTask
                .buildUseCase(profileMapper.from(profile))
                .map {
                    profile
                }
                .map { Resource.success(it) }
                .startWith(Resource.loading())
                .onErrorResumeNext(Function {
                    Observable.just(Resource.error(it.localizedMessage!!))
                })
                .toFlowable(BackpressureStrategy.LATEST)
                .toLiveData()

        mediatorSave.addSource(resource) {
            mediatorSave.value = it
            if (it.status != Status.LOADING) {
                mediatorSave.removeSource(resource)
            }
        }

    }

}