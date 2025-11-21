package com.nexthire.nexhire.API.cubo

import com.nexthire.nexhire.models.cubo.DimEmpresa
import com.nexthire.nexhire.models.cubo.DimEstado
import com.nexthire.nexhire.models.cubo.DimHabilidad
import com.nexthire.nexhire.models.cubo.DimMunicipio
import com.nexthire.nexhire.models.cubo.DimPlaza
import com.nexthire.nexhire.models.cubo.DimPostulante
import com.nexthire.nexhire.models.cubo.DimTiempo
import com.nexthire.nexhire.models.cubo.FactPostulaciones
import retrofit2.http.GET


interface ApiService {

    @GET("api/DimEmpresa/DTO")
    suspend fun getEmpresas(): List<DimEmpresa>

    @GET("api/DimEstado/DTO")
    suspend fun getEstados(): List<DimEstado>

    @GET("api/DimHabilidad/DTO")
    suspend fun getHabilidades(): List<DimHabilidad>

    @GET("api/DimMunicipio/DTO")
    suspend fun getMunicipios(): List<DimMunicipio>

    @GET("api/DimPlaza/DTO")
    suspend fun getPlazas(): List<DimPlaza>

    @GET("api/DimPostulante/DTO")
    suspend fun getPostulantes(): List<DimPostulante>

    @GET("api/DimTiempo/DTO")
    suspend fun getTiempos(): List<DimTiempo>

    @GET("api/HechosPostulaciones/DTO")
    suspend fun getHechosPostulaciones(): List<FactPostulaciones>
}