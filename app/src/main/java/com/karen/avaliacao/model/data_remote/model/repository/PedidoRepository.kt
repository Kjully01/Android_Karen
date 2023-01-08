package com.karen.avaliacao.model.data_remote.model.repository

import com.karen.avaliacao.model.data_remote.api.Api
import com.karen.avaliacao.model.data_remote.model.cliente.ClienteDataResponse
import com.karen.avaliacao.model.data_remote.model.cliente.ClienteResponse
import com.karen.avaliacao.model.data_remote.model.pedido.PedidoListResponse
import com.karen.avaliacao.model.data_remote.model.pedido.PedidoResponse
import com.karen.avaliacao.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class PedidoRepository {

    private var pedidoRoute = Api(Constants.BASE_URL).create()

    suspend fun getPedido(): Flow<PedidoListResponse> {
        return flow {
            pedidoRoute.getPedidos()
                .let { response ->
                    if (response.isSuccessful) {
                        response.body()
                    } else {
                        throw HttpException(response)
                    }
                }?.let {
                    emit(it)
                }
        }
    }
}