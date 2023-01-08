package com.karen.avaliacao.model.model.cliente

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cliente (
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @SerializedName("codigo")
    @ColumnInfo(name = "codigo")
    val codigo: String,

    @SerializedName("razao_social")
    @ColumnInfo(name = "razao_social")
    val razao_social: String,

    @SerializedName("nomeFantasia")
    @ColumnInfo(name = "nomeFantasia")
    val nomeFantasia: String,

    @SerializedName("cnpj")
    @ColumnInfo(name = "cnpj")
    val cnpj: String,

    @SerializedName("ramo_atividade")
    @ColumnInfo(name = "ramo_atividade")
    val ramo_atividade: String = "Não informado",

    @SerializedName("endereco")
    @ColumnInfo(name = "endereco")
    val endereco: String = "Não informado",

    @SerializedName("status")
    @ColumnInfo(name = "status")
    val status: String = "Indefinido",

    @SerializedName("contatos")
    @ColumnInfo(name = "contatos")
    val contatos: List<Contato>,
)