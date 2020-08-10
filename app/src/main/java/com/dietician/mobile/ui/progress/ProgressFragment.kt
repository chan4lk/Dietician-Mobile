package com.dietician.mobile.ui.progress

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import javax.inject.Inject


class ProgressFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val progressViewModel by viewModels<ProgressViewModel> { viewModelFactory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity()
            .application as DieticianApplication)
            .appComponent
            .progressComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_progress, container, false)
        val chart: LineChart = root.findViewById(R.id.chart1)
        val dataObjects = listOf(1F, 2F, 3F, 4F)
        val entries: MutableList<Entry> =
            ArrayList()
        for (data in dataObjects) {
            entries.add(Entry(data, data * 2))
        }

        val dataSet = LineDataSet(entries, "Label")
        dataSet.color = R.color.colorAccent
        dataSet.valueTextColor = R.color.colorPrimary
        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate() // refresh


        return root

    }
}