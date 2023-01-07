package com.karen.avaliacao.model.data_remote.model.pedido

import com.google.gson.annotations.SerializedName

data class PedidoResponse (
    @SerializedName("numero_ped_Rca")
    val numero_ped_Rca: Int,
    @SerializedName("numero_ped_erp")
    val numero_ped_erp: Int,
    @SerializedName("codigoCliente")
    val codigoCliente: Int,
    @SerializedName("NOMECLIENTE")
    val NOMECLIENTE: Int,
    @SerializedName("data")
    val data: Int,
    @SerializedName("status")
    val status: Int,
    @SerializedName("critica")
    val critica: Int,
    @SerializedName("tipo")
    val tipo: Int,
    @SerializedName("legendas")
    val legendas: List<String>
)