package com.nexthire.nexhire.API.departamento

import com.nexthire.nexhire.models.Departamento
import retrofit2.http.GET

interface ApiServices_Departamento {
    @GET("Departamentos")
    suspend fun getDepartamentos(): List<Departamento>
}