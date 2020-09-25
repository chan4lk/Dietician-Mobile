package com.dietician.mobile.ui.food

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.mobile.databinding.FragmentDietBinding
import com.dietician.presentation.model.Status
import com.dietician.presentation.viewmodels.FoodViewModel
import timber.log.Timber
import javax.inject.Inject

class FoodFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val foodViewModel by viewModels<FoodViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .foodComponent()
            .create()
            .inject(this)
    }

    val args: FoodFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val planId = args.planId
        Timber.d("planId %s", planId)
        val binding: FragmentDietBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_diet, container, false)
        val adapter = FoodAdapter(FoodListener {
            //Toast.makeText(context, "${nightId}", Toast.LENGTH_LONG).show()
        })

//        adapter.addHeaderAndSubmitList(foodItem)
        binding.foodListRecyclerView.adapter = adapter

        val manager = GridLayoutManager(activity, 3)
        manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {

            override fun getSpanSize(position: Int) = when (adapter.getItemViewType(position)) {
                ITEM_VIEW_TYPE_HEADER -> 3
                else -> 3
            }
        }
        binding.foodListRecyclerView.layoutManager = manager

        foodViewModel.load(planId)

        foodViewModel.source.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.loading.isVisible = false
                    it.data?.let { diet ->
                        adapter.addHeaderAndSubmitList(diet.foodItems)
                    }
                }
                Status.ERROR -> {
                    binding.loading.isVisible = false
                }

                Status.LOADING -> {
                    binding.loading.isVisible = true
                }
            }

        })

        return binding.root
    }

}