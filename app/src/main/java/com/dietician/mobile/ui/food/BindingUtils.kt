package com.dietician.mobile.ui.food

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dietician.mobile.R
import com.dietician.presentation.model.Food
import com.dietician.presentation.model.Header

@BindingAdapter("foodNameString")
fun TextView.setFoodNameString(item: Food?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("foodProteinString")
fun TextView.setFoodProteinString(item: Food?) {
    item?.let {
        text = item.protine.toBigDecimal().toPlainString()
    }
}

@BindingAdapter("foodCarbString")
fun TextView.setFoodCarbString(item: Food?) {
    item?.let {
        text = item.carbohydrate.toBigDecimal().toPlainString()
    }
}

@BindingAdapter("foodFatString")
fun TextView.setFoodFatString(item: Food?) {
    item?.let {
        text = item.fat.toBigDecimal().toPlainString()
    }
}

@BindingAdapter("foodImage")
fun ImageView.setSleepImage(item: Food?) {
    item?.let {
//        when (item.name) {
//            "Brown Rice (cooked)" -> print("x == 1")
//            "Egg Noodles" -> print("x == 2")
//            else -> { // Note the block
//                print("x is neither 1 nor 2")
//            }
//        }
        setImageResource(R.drawable.food2)
    }
}

@BindingAdapter("headerTitle")
fun TextView.setTitleString(item: Header?) {
    item?.let {
        text = item.title
    }
}