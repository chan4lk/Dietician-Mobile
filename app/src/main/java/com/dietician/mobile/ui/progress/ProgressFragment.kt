package com.dietician.mobile.ui.progress

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dietician.mobile.DieticianApplication
import com.dietician.mobile.R
import com.dietician.mobile.utils.formatToServerDateDefaults
import com.dietician.presentation.model.Progress
import com.dietician.presentation.model.Status
import com.dietician.presentation.viewmodels.ProgressViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class ProgressFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val progressViewModel by viewModels<ProgressViewModel> { viewModelFactory }
    private lateinit var chart: LineChart

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
        val loading: ProgressBar = root.findViewById(R.id.loading)
        val weightText: TextInputEditText = root.findViewById(R.id.weight_text)
        val button: MaterialButton = root.findViewById(R.id.update_btn)
        chart = root.findViewById(R.id.chart1)

        button.setOnClickListener {
            progressViewModel.save(
                weightText.text.toString().toDouble(),
                Date().formatToServerDateDefaults()
            )
        }

        progressViewModel.source.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.isVisible = true
                }
                Status.ERROR -> {
                    loading.isVisible = false
                }
                Status.SUCCESS -> {
                    loading.isVisible = false
                    it.data?.let { data ->
                        drawChart(data)
                    }

                }
            }
        })

        progressViewModel.saveSource.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    loading.isVisible = true
                }
                Status.ERROR -> {
                    loading.isVisible = false
                }
                Status.SUCCESS -> {
                    loading.isVisible = false
                    progressViewModel.load()
                }
            }
        })


        return root

    }

    private fun drawChart(progressData: List<Progress>) {
        val entries: MutableList<Entry> =
            ArrayList()
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())

        for (data in progressData) {
            val date = dateFormatter.parse(data.date)
            date?.let {
                entries.add(Entry(it.time.toFloat(), data.weight.toFloat()))
            }

        }

        val xAxis: XAxis = chart.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            private val mFormat =
                SimpleDateFormat("dd/MM", Locale.ENGLISH)

            override fun getFormattedValue(value: Float): String {
                return mFormat.format(Date(value.toLong()))
            }
        }

        val dataSet = LineDataSet(entries, "Label")
        dataSet.color = R.color.colorAccent
        dataSet.valueTextColor = R.color.colorPrimary
        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate() // refresh
    }
}