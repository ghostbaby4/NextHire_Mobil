package com.nexthire.nexhire.API.profesion

import com.nexthire.nexhire.models.Profesion
import retrofit2.http.GET

interface ApiServices_Profesion {
    @GET("Profesion")
    suspend fun getProfesion(): List<Profesion>
}