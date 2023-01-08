package com.karen.avaliacao.model.data_local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karen.avaliacao.model.model.cliente.Cliente

@Dao
interface ClienteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addClient(cliente: Cliente)

    @Query("SELECT * FROM CLIENTE")
    fun getClient(): List<Cliente>

    @Query("SELECT * FROM CLIENTE ORDER BY id DESC")
    fun readCliente(): LiveData<List<Cliente>>


}