package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.repository.TokenRepository
import com.dietician.domain.usecases.GetPlansTask
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.model.Plan
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import javax.inject.Inject

class PlanViewModel @Inject constructor(
    private val getPlansTask: GetPlansTask,
    private val tokenRepository: TokenRepository,
    private val planMapper: Mapper<PlanEntity, Plan>

) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val planMediator = MediatorLiveData<Resource<List<Plan>>>()

    val plansListSource: LiveData<Resource<List<Plan>>>
        get() = planMediator
    private val planResource = getPlansTask
        .buildUseCase(tokenRepository.getToken().id)
        .map { planEntities ->
            planEntities.map {
                planMapper.to(it)
            }
        }.map { Resource.success(it) }
        .startWith(Resource.loading())
        .onErrorResumeNext(Function {
            Observable.just(Resource.error(it.localizedMessage!!))
        }).toFlowable(BackpressureStrategy.LATEST)
        .toLiveData()

    init {
        planMediator.addSource(planResource) {
            planMediator.value = it
            if (it.status != Status.LOADING) {
                planMediator.removeSource(planResource)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}