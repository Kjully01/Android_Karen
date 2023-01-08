package com.karen.avaliacao.model.data_local.repository

import com.karen.avaliacao.model.data_local.dao.ClienteDao
import com.karen.avaliacao.model.model.cliente.Cliente

class ClienteRepositoryLocal(private val clienteDao: ClienteDao) {

    suspend fun addCliente(cliente: Cliente) {
        clienteDao.addClient(cliente)
    }

    suspend fun getCliente(): List<Cliente> {
        return clienteDao.getClient()
    }

}