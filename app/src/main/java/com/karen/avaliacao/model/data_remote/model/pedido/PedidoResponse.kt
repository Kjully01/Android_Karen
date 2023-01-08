package com.karen.avaliacao.model.data_remote.model.pedido

import com.google.gson.annotations.SerializedName

data class PedidoResponse (
    @SerializedName("numero_ped_Rca")
    val numero_ped_Rca: Int,
    @SerializedName("numero_ped_erp")
    val numero_ped_erp: String,
    @SerializedName("codigoCliente")
    val codigoCliente: String,
    @SerializedName("NOMECLIENTE")
    val NOMECLIENTE: String,
    @SerializedName("data")
    val data: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("critica")
    val critica: String,
    @SerializedName("tipo")
    val tipo: String,
    @SerializedName("legendas")
    val legendas: List<String>
)