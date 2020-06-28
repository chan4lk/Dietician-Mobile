package com.dietician.mobile.ui.plans

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dietician.mobile.R
import javax.inject.Inject

class PlanAdapter @Inject
constructor(): RecyclerView.Adapter<PlanAdapter.PlanViewHolder>(){

    private var planItemList: List<String> = emptyList()
    private  var clickListener: ClickListener? = null

    fun setPlanItem(planItems:List<String>){
        planItemList = planItems
    }

    fun setClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.plan_item, parent, false)
        return PlanViewHolder(view)
    }

    override fun getItemCount(): Int {
        return planItemList.size
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val planItem = planItemList[position]
        holder.bind(planItem)
    }

    interface ClickListener {
        fun onPlanItemClick(planItem: String)
    }


    inner class PlanViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        lateinit var selectedItem: String

        private var itemName: TextView? = null


        init {
            itemName = itemView.findViewById(R.id.item_name)
            itemView.setOnClickListener {
                clickListener?.onPlanItemClick(selectedItem)
            }
        }

        fun bind(planItem: String) {
            selectedItem = planItem
            itemName?.text = planItem
        }

    }

}