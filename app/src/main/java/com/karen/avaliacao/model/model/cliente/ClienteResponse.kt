package com.karen.avaliacao.model.model.cliente

import com.google.gson.annotations.SerializedName

data class ClienteResponse(
    @SerializedName("cliente")
    val cliente: Cliente
)