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
        when (item.name) {
            "Brown Rice (cooked)" -> setImageResource(R.drawable.brown_rice)
            "Egg Noodles" -> setImageResource(R.drawable.egg_noodles)
            "Rice Noodles(cooked)" -> setImageResource(R.drawable.rice_noodles)
            "Roast Chicken" -> setImageResource(R.drawable.roast_chicken)
            "Fride Rice" -> setImageResource(R.drawable.fride_rice)
            "Tuna Fish" -> setImageResource(R.drawable.tuna_fish)
            "Baked Fish" -> setImageResource(R.drawable.baked_fish)
            "Fish Sandwich" -> setImageResource(R.drawable.fish_sandwich)
            "Vegie Soup" -> setImageResource(R.drawable.veg_soup)
            "String Hopper" -> setImageResource(R.drawable.string_hoppers)
            "Dhal Curry" -> setImageResource(R.drawable.dhal_curry)
            "Salad Leaves" -> setImageResource(R.drawable.salad_leaves)
            "Baked Patatoes" -> setImageResource(R.drawable.baked_potatos)
            "Boiled Carrots" -> setImageResource(R.drawable.boiled_carrots)
            "Carrot (cooked)" -> setImageResource(R.drawable.carrot_cooked)
            "Carrot Salad" -> setImageResource(R.drawable.carrot_salad)
            "Seafood pasta salad" -> setImageResource(R.drawable.seafood_pasta_salad)
            "Dried Mango Slices" -> setImageResource(R.drawable.dried_mango)
            "Avacado" -> setImageResource(R.drawable.avacado)
            "Avacado Ice Cream" -> setImageResource(R.drawable.avacado_ice_cream)
            "Garlic Bread" -> setImageResource(R.drawable.galic_bread)
            "Banana Bread" -> setImageResource(R.drawable.banana_bread)
            "Bread" -> setImageResource(R.drawable.bread)
            "Pumkin (cooked)" -> setImageResource(R.drawable.pumkin_cooked)
            "Beet (cooked)" -> setImageResource(R.drawable.beet)
            "Salmon" -> setImageResource(R.drawable.salmon)
            "Banana" -> setImageResource(R.drawable.banana)
            "Roti" -> setImageResource(R.drawable.roti)
            "Chocolate Cake" -> setImageResource(R.drawable.chocalate_cake)
            "Egg & Cheese Sandwich" -> setImageResource(R.drawable.egg_cheese_sandwich)
            "Green Beans" -> setImageResource(R.drawable.green_beans)
            else -> { // Note the block
                setImageResource(R.drawable.food2)
            }
        }

    }
}

@BindingAdapter("headerTitle")
fun TextView.setTitleString(item: Header?) {
    item?.let {
        text = item.title
    }
}