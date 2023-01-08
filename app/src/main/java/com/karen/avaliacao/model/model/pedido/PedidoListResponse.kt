package com.karen.avaliacao.model.model.pedido

import com.google.gson.annotations.SerializedName

data class PedidoListResponse (
    @SerializedName("pedidos")
    val pedidosResponse: List<PedidoResponse>
)