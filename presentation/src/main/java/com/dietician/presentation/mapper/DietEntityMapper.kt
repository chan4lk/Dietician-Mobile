package com.dietician.presentation.mapper

import com.dietician.domain.entities.DietEntity
import com.dietician.domain.entities.FoodItemEntity
import com.dietician.presentation.model.Diet
import com.dietician.presentation.model.Food
import javax.inject.Inject

class DietEntityMapper @Inject constructor() : Mapper<DietEntity, Diet> {
    override fun from(e: Diet): DietEntity {
        return DietEntity(
            userId = e.userId,
            planId = e.planId,
            date = e.date,
            extraCalorieAmount = e.extraCalorieAmount,
            foodItems = e.foodItems.map { food ->
                FoodItemEntity(
                    id = food.id,
                    name = food.name,
                    carbohydrate = food.carbohydrate,
                    fat = food.fat,
                    protine = food.protine,
                    foodCategory = food.foodCategory,
                    foodQuantity = food.foodQuantity,
                    isVeg = food.isVeg,
                    type = food.type
                )
            },
            isError = e.isError,
            message = e.message
        )
    }

    override fun to(t: DietEntity): Diet {
        return Diet(
            userId = t.userId,
            planId = t.planId,
            date = t.date,
            extraCalorieAmount = t.extraCalorieAmount,
            foodItems = t.foodItems.map { food ->
                Food(
                    id = food.id,
                    name = food.name,
                    carbohydrate = food.carbohydrate,
                    fat = food.fat,
                    protine = food.protine,
                    foodCategory = food.foodCategory,
                    foodQuantity = food.foodQuantity,
                    isVeg = food.isVeg,
                    type = food.type
                )
            },
            isError = t.isError,
            message = t.message
        )
    }

}