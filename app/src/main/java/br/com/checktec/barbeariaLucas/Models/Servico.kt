package br.com.checktec.barbeariaLucas.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "servico")
class Servico : Serializable {

    @PrimaryKey
    var id:Long = 0
    var nome = ""
    var valor = ""


    override fun toString(): String {
        return "Servico(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}