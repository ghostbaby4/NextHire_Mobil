package com.nexthire.nexhire.API.departamento

import com.nexthire.nexhire.API.departamento.ApiServices_Departamento
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient_Departamento{
    private const val BASE_URL =
        "https://webapinexthiremongo20251120215652-eyd4fkgyd2hndvem.canadacentral-01.azurewebsites.net/api/"

    fun create(): ApiServices_Departamento {
        val logger = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val ok = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(ok)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices_Departamento::class.java)
    }
}