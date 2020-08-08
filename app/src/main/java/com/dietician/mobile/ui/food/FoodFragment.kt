package com.dietician.mobile.ui.food

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.mobile.databinding.FragmentDietBinding
import com.dietician.presentation.model.Food
import javax.inject.Inject

class FoodFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val foodViewModel by viewModels<FoodViewModel> { viewModelFactory }

    private val foodItem: List<Food> = listOf(
        Food(4, "Coffee", 1),
        Food(9, "Coffee", 1),
        Food(10, "Coffee", 1),
        Food(11, "Coffee", 1),
        Food(5, "Rice", 2),
        Food(12, "Rice", 2),
        Food(13, "Rice", 2),
        Food(14, "Rice", 2),
        Food(15, "Rice", 2),
        Food(6, "Milk",3),
        Food(7, "Cheese", 1),
        Food(8, "Cake", 3)
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .foodComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding: FragmentDietBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_diet, container, false)
        val adapter = FoodAdapter(FoodListener {
            //Toast.makeText(context, "${nightId}", Toast.LENGTH_LONG).show()
        })

        adapter.addHeaderAndSubmitList(foodItem)
        binding.foodListRecyclerView.adapter = adapter

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int) =  when (adapter.getItemViewType(position)) {
                ITEM_VIEW_TYPE_HEADER -> 3
                else -> 1
            }
        }
        binding.foodListRecyclerView.layoutManager = manager
        return binding.root
    }

}