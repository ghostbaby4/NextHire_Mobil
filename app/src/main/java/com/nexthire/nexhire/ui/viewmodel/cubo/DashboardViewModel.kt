package com.nexthire.nexhire.ui.viewmodel.cubo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nexthire.nexhire.API.cubo.ApiService
import com.nexthire.nexhire.API.cubo.RetrofitInstance
import com.nexthire.nexhire.models.cubo.DimEmpresa
import com.nexthire.nexhire.models.cubo.DimEstado
import com.nexthire.nexhire.models.cubo.DimHabilidad
import com.nexthire.nexhire.models.cubo.DimMunicipio
import com.nexthire.nexhire.models.cubo.DimPlaza
import com.nexthire.nexhire.models.cubo.DimPostulante
import com.nexthire.nexhire.models.cubo.DimTiempo
import com.nexthire.nexhire.models.cubo.FactPostulaciones
import com.nexthire.nexhire.models.cubo.PostulacionCompleta
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val api: ApiService = RetrofitInstance.api
) : ViewModel() {

    var loading = androidx.compose.runtime.mutableStateOf(true)
        private set

    var errorMessage = androidx.compose.runtime.mutableStateOf<String?>(null)
        private set

    var datosCompletos =
        androidx.compose.runtime.mutableStateOf<List<PostulacionCompleta>>(emptyList())
        private set


    init {
        cargarDatos()
    }

    fun cargarDatos() {
        viewModelScope.launch {
            loading.value = true
            errorMessage.value = null

            try {
                val empresas = api.getEmpresas()
                val estados = api.getEstados()
                val habilidades = api.getHabilidades()
                val municipios = api.getMunicipios()
                val plazas = api.getPlazas()
                val postulantes = api.getPostulantes()
                val tiempos = api.getTiempos()

                val hechos = api.getHechosPostulaciones()

                datosCompletos.value = unirTablas(
                    hechos,
                    empresas,
                    estados,
                    habilidades,
                    municipios,
                    plazas,
                    postulantes,
                    tiempos
                )

            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = "Error al cargar datos: ${e.message}"
            } finally {
                loading.value = false
            }
        }
    }

    private fun unirTablas(
        hechos: List<FactPostulaciones>,
        empresas: List<DimEmpresa>,
        estados: List<DimEstado>,
        habilidades: List<DimHabilidad>,
        municipios: List<DimMunicipio>,
        plazas: List<DimPlaza>,
        postulantes: List<DimPostulante>,
        tiempos: List<DimTiempo>
    ): List<PostulacionCompleta> {

        return hechos.map { h ->
            PostulacionCompleta(
                empresa = empresas.firstOrNull { it.ID_Empresa == h.ID_Empresa },
                estado = estados.firstOrNull { it.ID_Estado == h.ID_Estado },
                habilidad = habilidades.firstOrNull { it.ID_Habilidades == h.ID_Habilidades },
                municipio = municipios.firstOrNull { it.ID_Municipio == h.ID_Municipio },
                plaza = plazas.firstOrNull { it.ID_Plaza == h.ID_Plaza },
                postulante = postulantes.firstOrNull { it.ID_Postulante == h.ID_Postulante },
                fecha = tiempos.firstOrNull { it.ID_Fecha == h.ID_Fecha },
                valor = h.Valor
            )
        }
    }

    fun postulacionesPorEmpresa(): Map<String, Int> {
        return datosCompletos.value
            .groupBy { it.empresa?.Nombre_Empresa ?: "Desconocida" }
            .mapValues { (_, lista) ->
                lista.sumOf { it.valor }
            }
    }

    fun postulacionesPorMunicipio(): Map<String, Int> {
        return datosCompletos.value
            .groupBy { it.municipio?.Nombre_Municipio ?: "Sin municipio" }
            .mapValues { (_, lista) ->
                lista.sumOf { it.valor }
            }
    }

    fun postulacionesPorHabilidad(): Map<String, Int> {
        return datosCompletos.value
            .groupBy { it.habilidad?.Nombre_Habilidad ?: "Sin habilidad" }
            .mapValues { (_, lista) ->
                lista.sumOf { it.valor }
            }
    }

    fun postulacionesPorEstado(): Map<String, Int> {
        return datosCompletos.value
            .groupBy { it.estado?.Descripcion ?: "Desconocido" }
            .mapValues { (_, lista) ->
                lista.sumOf { it.valor }
            }
    }

    fun postulacionesPorMes(): Map<String, Int> {
        return datosCompletos.value
            .groupBy { it.fecha?.Nombre_Mes ?: "Sin mes" }
            .mapValues { (_, lista) ->
                lista.sumOf { it.valor }
            }
    }

    fun topEmpresas(limit: Int = 5): List<Pair<String, Int>> {
        return postulacionesPorEmpresa()
            .toList()
            .sortedByDescending { it.second }
            .take(limit)
    }

    fun topHabilidades(limit: Int = 5): List<Pair<String, Int>> {
        return postulacionesPorHabilidad()
            .toList()
            .sortedByDescending { it.second }
            .take(limit)
    }
}