package com.nexthire.nexhire.API.habilidades

import com.nexthire.nexhire.API.habilidades.ApiServices_Habilidades
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient_Habilidades{
    private const val BASE_URL =
        "https://webapinexthiremongo20251120215652-eyd4fkgyd2hndvem.canadacentral-01.azurewebsites.net/api/"

    fun create(): ApiServices_Habilidades {
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
            .create(ApiServices_Habilidades::class.java)
    }
}