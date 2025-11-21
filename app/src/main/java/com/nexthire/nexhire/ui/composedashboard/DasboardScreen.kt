package com.nexthire.nexhire.ui.composedashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nexthire.nexhire.ui.viewmodel.cubo.DashboardViewModel
import com.nexthire.nexhire.ui.composedashboard.BarChartView
import com.nexthire.nexhire.ui.composedashboard.LineChartView
import com.nexthire.nexhire.ui.composedashboard.PieChartView
import com.github.mikephil.charting.data.BarEntry
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight


@Composable
fun DashboardScreen(viewModel: DashboardViewModel) {

    val loading by viewModel.loading
    val error by viewModel.errorMessage
    val datos = viewModel.datosCompletos.value

    when {
        loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        error != null -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = error ?: "Error desconocido", color = MaterialTheme.colorScheme.error)
        }

        else -> DashboardContent(viewModel)
    }
}

@Composable
fun DashboardContent(viewModel: DashboardViewModel) {

    val empresas = viewModel.postulacionesPorEmpresa()
    val habilidades = viewModel.topHabilidades()
    val porMes = viewModel.postulacionesPorMes()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        item {
            Text(
                "Dashboard de Postulaciones",
                style = MaterialTheme.typography.titleLarge.copy(
                    color = Color(0xFF192338)
                )
            )
            Spacer(Modifier.height(6.dp))
            Divider()
        }


        item {
            DashboardCard(title = "Habilidades más demandadas") {

                PieChartView(
                    data = habilidades,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }

        item {
            DashboardCard(title = "Postulaciones por mes") {

                LineChartView(
                    data = porMes,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }

        item {
            val estados = viewModel.postulacionesPorEstado()

            DashboardCard(title = "Postulaciones por estado") {

                val entries = estados.values.mapIndexed { index, value ->
                    BarEntry(index.toFloat(), value.toFloat())
                }
                val labels = estados.keys.toList()

                HorizontalBarChartView(
                    entries = entries,
                    labels = labels,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }
        item {
            DashboardCard(title = "Postulaciones por empresa") {

                val entries = empresas.values.mapIndexed { index, value ->
                    BarEntry(index.toFloat(), value.toFloat())
                }
                val labels = empresas.keys.toList()

                BarChartView(
                    entries = entries,
                    labels = labels,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                )
            }
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White //
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {

            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF192338)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            content()
        }
    }
}