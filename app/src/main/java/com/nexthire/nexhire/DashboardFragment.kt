package com.nexthire.nexhire.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.gson.Gson
import com.nexthire.nexhire.R
import com.nexthire.nexhire.models.PostulacionesEstado
import com.nexthire.nexhire.models.PostulacionesMunicipioStats
import com.nexthire.nexhire.models.PostulacionesStats


class DashboardFragment : Fragment() {

    private lateinit var barChartEmpresas: BarChart
    private lateinit var barChartMunicipios: BarChart
    private lateinit var pieChartEstados: PieChart
    private lateinit var tvTotalGeneral: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        barChartEmpresas = view.findViewById(R.id.barChartPostulaciones)
        barChartMunicipios = view.findViewById(R.id.barChartMunicipios)
        pieChartEstados = view.findViewById(R.id.pieChartEstados)
        tvTotalGeneral = view.findViewById(R.id.tvTotalGeneral)

        mostrarGraficoPostulacionesEmpresas()
        mostrarGraficoPostulacionesMunicipios()
        mostrarGraficoPostulacionesEstados()

        return view
    }

    private fun obtenerDatosEmpresas(context: Context): List<PostulacionesStats>? {
        return try {
            val json = context.assets.open("PostulacionesStats.json")
                .bufferedReader().use { it.readText() }
            Gson().fromJson(json, Array<PostulacionesStats>::class.java).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun obtenerDatosMunicipios(context: Context): List<PostulacionesMunicipioStats>? {
        return try {
            val json = context.assets.open("PostulacionesMunicipioStats.json")
                .bufferedReader().use { it.readText() }
            Gson().fromJson(json, Array<PostulacionesMunicipioStats>::class.java).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun obtenerDatosEstados(context: Context): List<PostulacionesEstado>? {
        return try {
            val json = context.assets.open("PostulacionesEstado.json")
                .bufferedReader().use { it.readText() }
            Gson().fromJson(json, Array<PostulacionesEstado>::class.java).toList()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun mostrarGraficoPostulacionesEmpresas() {
        val datos = obtenerDatosEmpresas(requireContext()) ?: return

        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()
        var totalGeneral = 0

        datos.forEachIndexed { index, item ->
            entries.add(BarEntry(index.toFloat(), item.total_postulaciones.toFloat()))
            labels.add(item.empresa)
            totalGeneral += item.total_postulaciones
        }

        val dataSet = BarDataSet(entries, "Postulaciones por Empresa")
        dataSet.colors = ColorTemplate.LIBERTY_COLORS.toList()
        dataSet.valueTextSize = 12f

        val barData = BarData(dataSet)
        barChartEmpresas.data = barData
        barChartEmpresas.description.isEnabled = false
        barChartEmpresas.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        barChartEmpresas.xAxis.granularity = 1f
        barChartEmpresas.xAxis.isGranularityEnabled = true
        barChartEmpresas.animateY(1000)
        barChartEmpresas.invalidate()

        tvTotalGeneral.text = "Total general: $totalGeneral"
    }

    private fun mostrarGraficoPostulacionesMunicipios() {
        val datos = obtenerDatosMunicipios(requireContext()) ?: return

        val entries = ArrayList<BarEntry>()
        val labels = ArrayList<String>()

        datos.forEachIndexed { index, item ->
            entries.add(BarEntry(index.toFloat(), item.totalPostulaciones.toFloat()))
            labels.add(item.nombreMunicipio)
        }

        val dataSet = BarDataSet(entries, "Postulaciones por Municipio")
        dataSet.colors = ColorTemplate.LIBERTY_COLORS.toList()
        dataSet.valueTextSize = 12f

        val barData = BarData(dataSet)
        barChartMunicipios.data = barData
        barChartMunicipios.description.isEnabled = false
        barChartMunicipios.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        barChartMunicipios.xAxis.granularity = 1f
        barChartMunicipios.xAxis.isGranularityEnabled = true
        barChartMunicipios.animateY(1000)
        barChartMunicipios.invalidate()
    }

    private fun mostrarGraficoPostulacionesEstados() {
        val datos = obtenerDatosEstados(requireContext()) ?: return

        val entries = ArrayList<PieEntry>()
        datos.forEach { item ->
            entries.add(PieEntry(item.total.toFloat(), item.estado))
        }

        val dataSet = PieDataSet(entries, "Postulaciones por Estado")
        dataSet.colors = ColorTemplate.LIBERTY_COLORS.toList()
        dataSet.valueTextSize = 14f

        val pieData = PieData(dataSet)

        pieChartEstados.data = pieData
        pieChartEstados.setUsePercentValues(true)
        pieChartEstados.description.isEnabled = false
        pieChartEstados.centerText = "Distribución por Estado"
        pieChartEstados.setEntryLabelColor(android.graphics.Color.BLACK)
        pieChartEstados.animateY(1000)
        pieChartEstados.invalidate()
    }
}