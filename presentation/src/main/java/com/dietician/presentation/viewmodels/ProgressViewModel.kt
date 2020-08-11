package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.entities.ProgressEntity
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.GetProgressTask
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.model.Progress
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class ProgressViewModel @Inject constructor(
    getProgressTask: GetProgressTask,
    private val mapper: Mapper<ProgressEntity, Progress>,
    tokenRepository: TokenRepository
) : ViewModel() {
    private val mediator = MediatorLiveData<Resource<List<Progress>>>()

    val source: LiveData<Resource<List<Progress>>>
        get() = mediator

    init {
        val resource =
            getProgressTask.buildUseCase(tokenRepository.getToken().id)
                .map {
                    it.map { progress ->
                        mapper.to(progress)
                    }
                }
                .map { Resource.success(it) }
                .startWith(Resource.loading())
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