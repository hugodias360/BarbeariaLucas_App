package br.com.checktec.barbeariaLucas.Models

import com.google.gson.GsonBuilder
import java.io.Serializable

class Cliente : Serializable {

    var id:Long = 0
    var apelido = ""
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