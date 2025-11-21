package com.nexthire.nexhire.models.cubo

data class DimTiempo(
    val ID_Fecha: Int,
    val Fecha: String,
    val Dia: Int,
    val Nombre_Dia: String,
    val Mes: Int,
    val Nombre_Mes: String,
    val Trimestre: Int,
    val Año: Int,
    val Semana: Int
)