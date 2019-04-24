package br.com.checktec.barbeariaLucas.Models

import com.google.gson.GsonBuilder
import java.io.Serializable

class Servico : Serializable {

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