package com.karen.avaliacao.model.data_remote.model.cliente

import com.google.gson.annotations.SerializedName

data class ClienteResponse(
    @SerializedName("cliente")
    val cliente: ClienteDataResponse
)