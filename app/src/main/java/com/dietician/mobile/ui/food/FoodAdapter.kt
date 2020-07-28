package com.dietician.mobile.ui.food

import android.annotation.SuppressLint
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dietician.mobile.R
import com.dietician.mobile.databinding.HeaderBinding
import com.dietician.mobile.databinding.ListItemFoodBinding
import com.dietician.presentation.model.Food
import com.dietician.presentation.model.Header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

internal val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class FoodAdapter (val clickListener: FoodListener): ListAdapter<DataItem, RecyclerView.ViewHolder>(FoodDiffCallback()){

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Food>?) {
        adapterScope.launch {
            val bfFood = mutableListOf<Food>()
            val lFood = mutableListOf<Food>()
            val dFood = mutableListOf<Food>()
            val h0 = Header(id=1, title = "There is no diet menu")
            val h1 = Header(id=1, title = "Break First")
            val h2 = Header(id=2, title = "Lunch")
            val h3 = Header(id=3, title = "Dinner")
            val items = when (list) {
                null -> listOf(DataItem.HeaderItem(h0))
                else ->
                {
                    for(fd in list) {
                        when(fd.headerId){
                            1-> bfFood.add(fd)
                            2-> lFood.add(fd)
                            else-> dFood.add(fd)
                        }
                    }
                    listOf(DataItem.HeaderItem(h1)) + bfFood.map { DataItem.FoodItem(it) } + listOf(DataItem.HeaderItem(h2)) + lFood.map { DataItem.FoodItem(it) }+ listOf(DataItem.HeaderItem(h3)) + dFood.map { DataItem.FoodItem(it) }
                }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder ->{
                val foodItem = getItem(position) as DataItem.FoodItem
                holder.bind(foodItem.food, clickListener)
            }
            is TextViewHolder ->{
                val headerItem = getItem(position) as DataItem.HeaderItem
                holder.bindHeader(headerItem.header)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.HeaderItem -> ITEM_VIEW_TYPE_HEADER
            is DataItem.FoodItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(val binding: HeaderBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindHeader(item: Header){
            binding.title = item
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HeaderBinding.inflate(layoutInflater, parent, false)
                return TextViewHolder(binding)
            }
        }
    }

    class ViewHolder private constructor(val binding: ListItemFoodBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item: Food, clickListener: FoodListener){
            binding.food =item
            binding.clickListener =clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder{
                val layoutInflater =LayoutInflater.from(parent.context)
                val binding = ListItemFoodBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class FoodDiffCallback : DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }

}

class FoodListener(val clickListener: (dietId: Long)-> Unit){
    fun onClick (food: Food) = clickListener(food.id)

}

sealed class DataItem {

    data class FoodItem(val food: Food): DataItem() {
        override val id = food.id
    }

    data class HeaderItem(var header: Header): DataItem() {
        var title = header.title
        override val id = header.id
    }

    abstract val id: Long
}