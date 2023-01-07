package com.karen.avaliacao.model.data_remote.api

import com.karen.avaliacao.model.data_remote.model.cliente.ClienteResponse
import com.karen.avaliacao.model.data_remote.model.pedido.PedidoListResponse
import retrofit2.Response
import retrofit2.http.GET

interface RoutesApi {
    @GET("cliente")
    suspend fun getCliente(): Response<ClienteResponse>

    @GET("pedido")
    suspend fun getPedidos(): Response<PedidoListResponse>
}