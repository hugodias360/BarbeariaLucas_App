package br.com.checktec.barbeariaLucas.Service

import android.content.Context
import android.util.Log
import br.com.checktec.barbeariaLucas.Models.Cliente
import br.com.checktec.barbeariaLucas.Utils.AndroidUtils
import br.com.checktec.barbeariaLucas.Utils.Host
import br.com.checktec.barbeariaLucas.Utils.HttpHelper

import android.widget.Toast
import br.com.checktec.barbeariaLucas.Utils.Response
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ClienteService {
    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getCliente (context: Context): List<Cliente> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apicliente"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            return parserJson<List<Cliente>>(json)
        } else {
            return ArrayList<Cliente>()
        }

    }
    fun save(cliente: Cliente): Response {
        val json = HttpHelper.post("$host/apicliente/", cliente.toJson())
        Log.d(TAG, json)
        return parserJson<Response>(json)
    }
    fun delete(servico: Cliente): Response {
        val url = "$host/apicliente/${servico.id}"
        val json = HttpHelper.delete(url)
        return parserJson<Response>(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}