package com.dietician.mobile.ui.food

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dietician.mobile.R
import com.dietician.presentation.model.Food

@BindingAdapter("foodNameString")
fun TextView.setFoodNameString(item: Food?) {
    item?.let {
        text = item.name
    }
}

@BindingAdapter("foodImage")
fun ImageView.setSleepImage(item: Food?) {
    item?.let {
        setImageResource(when (item.name) {
            "F1" -> R.drawable.ic_fastfood_24
            "F2" -> R.drawable.ic_fastfood_24
            "F3" -> R.drawable.ic_fastfood_24
            else -> R.drawable.ic_fastfood_24
        })
    }
}