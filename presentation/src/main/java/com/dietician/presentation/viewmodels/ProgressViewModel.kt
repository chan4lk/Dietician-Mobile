package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.entities.ProgressEntity
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.GetProgressTask
import com.dietician.domain.usecases.SaveProgressTask
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.model.Progress
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class ProgressViewModel @Inject constructor(
    private val getProgressTask: GetProgressTask,
    private val saveProgressTask: SaveProgressTask,
    private val mapper: Mapper<ProgressEntity, Progress>,
    private val tokenRepository: TokenRepository
) : ViewModel() {
    private val mediator = MediatorLiveData<Resource<List<Progress>>>()

    val source: LiveData<Resource<List<Progress>>>
        get() = mediator

    private val mediatorSave = MediatorLiveData<Resource<Long>>()

    val saveSource: LiveData<Resource<Long>>
        get() = mediatorSave

    init {
        load()

    }

    fun load() {
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

    fun save(weight: Double, dateString: String) {
        val progressData = Progress(
            id = 0,
            date = dateString,
            weight = weight,
            userId = tokenRepository.getToken().id
        )
        val resource =
            saveProgressTask.buildUseCase(mapper.from(progressData))
                .map { Resource.success(it) }
                .startWith(Resource.loading())
                .onErrorResumeNext(
                    Function {
                        Observable.just(Resource.error(it.localizedMessage!!))
                    }
                ).toFlowable(BackpressureStrategy.LATEST)
                .toLiveData()
        mediatorSave.addSource(resource) {
            mediatorSave.value = it
            if (it.status != Status.LOADING) {
                mediatorSave.removeSource(resource)
            }
        }
    }

}