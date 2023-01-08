package com.karen.avaliacao.model.data_local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.karen.avaliacao.model.model.cliente.Contato

class Converters {

    @TypeConverter
    fun toJson(listContato: List<Contato>?): String {
        return Gson().toJson(listContato)
    }

    @TypeConverter
    fun toList(jsonContato: String): List<Contato> {
        val objects = Gson().fromJson(jsonContato, Array<Contato>::class.java) as Array<Contato>
        val listContato = objects.toList()
        return listContato
    }

    @TypeConverter
    fun toJsonLegenda(listLegenda: List<String>?): String {
        return Gson().toJson(listLegenda)
    }

    @TypeConverter
    fun toListLegenda(jsonLegenda: String): List<String> {
        val objects = Gson().fromJson(jsonLegenda, Array<String>::class.java) as Array<String>
        val listLegenda = objects.toList()
        return listLegenda
    }

}