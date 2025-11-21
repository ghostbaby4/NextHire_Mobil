package com.nexthire.nexhire.models.cubo

data class DimPostulante(
    val ID_Postulante: Int,
    val Cedula: String,
    val Nombre: String,
    val Apellidos: String,
    val Sexo: String,
    val Correo: String,
    val Telefono: String,
    val Fecha_Nacimiento: String,
    val Direccion: String,
    val Experiencia_Laboral: String
)