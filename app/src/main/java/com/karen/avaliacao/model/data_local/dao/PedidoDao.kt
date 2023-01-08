package com.karen.avaliacao.model.data_local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karen.avaliacao.model.model.pedido.Pedido

@Dao
interface PedidoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPedido(pedido: Pedido)

    @Query("SELECT * FROM PEDIDO")
    fun getPedido(): LiveData<List<Pedido>>
}