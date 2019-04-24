package br.com.checktec.barbeariaLucas.Service

import android.content.Context
import android.util.Log
import br.com.checktec.barbeariaLucas.Utils.HttpHelper
import br.com.checktec.barbeariaLucas.Models.Servico
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Utils.Response
import br.com.checktec.barbeariaLucas.Utils.AndroidUtils
import br.com.checktec.barbeariaLucas.Utils.Host
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ServicoService {

    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getServicos (context: Context): List<Servico> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apiservico"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            return parserJson<List<Servico>>(json)
        } else {
            return ArrayList<Servico>()
        }

    }
    fun save(servico: Servico): Response {
        val json = HttpHelper.post("$host/servicos", servico.toJson())
        return parserJson<Response>(json)
    }
    fun delete(servico: Servico): Response {
        val url = "$host/servicos/${servico.id}"
        val json = HttpHelper.delete(url)
        return parserJson<Response>(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

}