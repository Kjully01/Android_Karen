package com.karen.avaliacao.model.data_local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karen.avaliacao.model.data_local.dao.ClienteDao
import com.karen.avaliacao.model.data_local.dao.PedidoDao
import com.karen.avaliacao.model.model.cliente.Cliente
import com.karen.avaliacao.model.model.pedido.Pedido

@Database(entities = [Cliente::class, Pedido::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class DatabaseLocal : RoomDatabase(){

    abstract fun clientDao(): ClienteDao
    abstract fun pedidoDao(): PedidoDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseLocal? = null

        fun getDatabase(context: Context): DatabaseLocal{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseLocal::class.java,
                    "database_maxima"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}