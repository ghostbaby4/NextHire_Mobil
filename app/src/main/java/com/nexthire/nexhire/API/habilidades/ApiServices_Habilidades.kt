package com.nexthire.nexhire.API.habilidades

import com.nexthire.nexhire.models.Habilidades
import retrofit2.http.GET

interface ApiServices_Habilidades {
    @GET("Habilidades")
    suspend fun getHabilidades(): List<Habilidades>
}