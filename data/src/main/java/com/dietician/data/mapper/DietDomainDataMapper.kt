package com.dietician.data.mapper

import com.dietician.data.model.DietData
import com.dietician.data.model.FoodData
import com.dietician.domain.entities.DietEntity
import com.dietician.domain.entities.FoodItemEntity
import javax.inject.Inject

class DietDomainDataMapper @Inject constructor() : Mapper<DietEntity, DietData> {
    override fun from(e: DietData): DietEntity {
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

    override fun to(t: DietEntity): DietData {
        return DietData(
            userId = t.userId,
            planId = t.planId,
            date = t.date,
            extraCalorieAmount = t.extraCalorieAmount,
            foodItems = t.foodItems.map { food ->
                FoodData(
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

    override fun from(e: DietData, userId: Long): DietEntity {
        return DietEntity(
            userId = userId,
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

    override fun to(t: DietEntity, userId: Long): DietData {
        return DietData(
            userId = userId,
            planId = t.planId,
            date = t.date,
            extraCalorieAmount = t.extraCalorieAmount,
            foodItems = t.foodItems.map { food ->
                FoodData(
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