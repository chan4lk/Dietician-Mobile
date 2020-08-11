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

@BindingAdapter("foodImage")
fun ImageView.setSleepImage(item: Food?) {
    item?.let {
        setImageResource(R.drawable.food2)
    }
}

@BindingAdapter("headerTitle")
fun TextView.setTitleString(item: Header?) {
    item?.let {
        text = item.title
    }
}