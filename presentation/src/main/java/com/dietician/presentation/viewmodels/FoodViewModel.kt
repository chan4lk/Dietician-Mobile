package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.entities.DietEntity
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.GetDietTask
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.model.Diet
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class FoodViewModel @Inject constructor(
    private val getDietTask: GetDietTask,
    private val tokenRepository: TokenRepository,
    private val mapper: Mapper<DietEntity, Diet>

) : ViewModel() {

    private val mediator = MediatorLiveData<Resource<Diet>>()

    val source: LiveData<Resource<Diet>>
        get() = mediator

    fun load(planId: Long) {
        val params = GetDietTask.Params(tokenRepository.getToken().id, planId)
        val resource = getDietTask
            .buildUseCase(params)
            .map { diet ->
                mapper.to(diet)
            }.map { Resource.success(it) }
            .startWith(Resource.loading())
            .onErrorResumeNext(Function {
                Observable.just(Resource.error(it.localizedMessage!!))
            }).toFlowable(BackpressureStrategy.LATEST)
            .toLiveData()
        mediator.addSource(resource) {
            mediator.value = it
            if (it.status != Status.LOADING) {
                mediator.removeSource(resource)
            }
        }
    }

}