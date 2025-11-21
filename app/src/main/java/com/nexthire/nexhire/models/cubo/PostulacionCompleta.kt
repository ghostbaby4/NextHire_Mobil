package com.nexthire.nexhire.models.cubo

data class PostulacionCompleta(
    val empresa: DimEmpresa?,
    val estado: DimEstado?,
    val habilidad: DimHabilidad?,
    val municipio: DimMunicipio?,
    val plaza: DimPlaza?,
    val postulante: DimPostulante?,
    val fecha: DimTiempo?,
    val valor: Int
)