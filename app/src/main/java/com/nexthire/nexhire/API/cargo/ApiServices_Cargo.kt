package com.nexthire.nexhire.API.cargo

import com.nexthire.nexhire.models.Cargos
import retrofit2.http.GET

interface ApiServices_Cargo {
    @GET("Cargo")
    suspend fun getCargo(): List<Cargos>
}

