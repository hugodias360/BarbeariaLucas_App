package br.com.checktec.barbeariaLucas.Models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "agenda")
class Agenda :Serializable{
    @PrimaryKey
    var id:Long = 0
    var status = ""
    var data_inicio = ""
    var data_fim = ""
    var clientes = ""
    var cabeleleiros = ""
    var servicos = ""

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}