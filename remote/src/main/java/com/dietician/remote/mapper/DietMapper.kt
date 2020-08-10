package com.dietician.remote.mapper

import com.dietician.data.model.DietData
import com.dietician.data.model.FoodData
import com.dietician.remote.model.Diet
import com.dietician.remote.model.Food
import javax.inject.Inject

class DietMapper @Inject constructor() : Mapper<DietData, Diet> {
    override fun from(e: Diet): DietData {
        return DietData(
            userId = e.userId,
            planId = e.planId,
            date = e.date,
            extraCalorieAmount = e.extraCalorieAmount,
            foodItems = e.foodItems.map { food ->
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
            isError = e.isError,
            message = e.message
        )
    }

    override fun to(t: DietData): Diet {
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