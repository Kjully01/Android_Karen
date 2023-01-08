package com.karen.avaliacao.model.data_local.repository

import androidx.lifecycle.LiveData
import com.karen.avaliacao.model.data_local.dao.PedidoDao
import com.karen.avaliacao.model.model.pedido.Pedido

class PedidoRepositoryLocal(private val pedidoDao: PedidoDao) {

    var getPedido: LiveData<List<Pedido>> = pedidoDao.getPedido()

    suspend fun addPedido(pedido: Pedido) {
        pedidoDao.addPedido(pedido)
    }

}