package com.dietician.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import com.dietician.domain.entities.PlanEntity
import com.dietician.domain.usecases.SavePlanTask
import com.dietician.presentation.mapper.Mapper
import com.dietician.presentation.model.Plan
import com.dietician.presentation.model.Resource
import com.dietician.presentation.model.Status
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.functions.Function
import javax.inject.Inject

class AddPlanViewModel @Inject constructor(
    private val savePlanTask: SavePlanTask,
    private val planMapper: Mapper<PlanEntity, Plan>
) : ViewModel() {

    private val mediator = MediatorLiveData<Resource<Long>>()

    val source: LiveData<Resource<Long>>
        get() = mediator

    fun save(plan: Plan) {

        val resource =
            savePlanTask.buildUseCase(planMapper.from(plan))
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