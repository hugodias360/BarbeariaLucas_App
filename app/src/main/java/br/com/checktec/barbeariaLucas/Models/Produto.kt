package br.com.checktec.barbeariaLucas.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "produto")
class Produto : Serializable {
    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var quantidade = ""
    var valor_unitario = ""
    var especificacao = ""
    var validade_produto = ""


    override fun toString(): String {
        return "Nome: $nome"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}