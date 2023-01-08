package com.karen.avaliacao.model.data_remote.model.cliente

import com.google.gson.annotations.SerializedName

data class ContatoResponse (
    @SerializedName("nome")
    val nome: String,
    @SerializedName("telefone")
    val telefone: String,
    @SerializedName("celular")
    val celular: String,
    @SerializedName("conjuge")
    val conjuge: String,
    @SerializedName("tipo")
    val tipo: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("e_mail")
    val email: String,
    @SerializedName("data_nascimento")
    val data_nascimento: String,
    @SerializedName("dataNascimentoConjuge")
    val dataNascimentoConjuge: String,
)