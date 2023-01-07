package com.karen.avaliacao.model.data_remote.model.cliente

import com.google.gson.annotations.SerializedName

data class ClienteResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("codigo")
    val codigo: String,
    @SerializedName("razao_social")
    val razao_social: String,
    @SerializedName("nomeFantasia")
    val nomeFantasia: String,
    @SerializedName("cnpj")
    val cnpj: String,
    @SerializedName("ramo_atividade")
    val ramo_atividade: String,
    @SerializedName("endereco")
    val endereco: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("contatos")
    val contatos: List<ContatoResponse>,
)