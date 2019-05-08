package br.com.checktec.barbeariaLucas.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "cabeleleiro")
class Cabeleleiro : Serializable {
    @PrimaryKey
    var id:Long = 0
    var senha = ""
    var nome = ""
    var email = ""
    var celular = ""

    override fun toString(): String {
        return "Servico(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}