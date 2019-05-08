package br.com.checktec.barbeariaLucas.Service

import android.content.Context
import android.util.Log
import br.com.checktec.barbeariaLucas.Database.DatabaseManager
import br.com.checktec.barbeariaLucas.Utils.HttpHelper
import br.com.checktec.barbeariaLucas.Models.Servico
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Utils.Response
import br.com.checktec.barbeariaLucas.Utils.AndroidUtils
import br.com.checktec.barbeariaLucas.Utils.Host
import br.com.fernandosousa.lmsapp.BarberApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ServicoService {

    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getServicos (context: Context): List<Servico> {
        var servicos = ArrayList<Servico>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apiservico"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            servicos = parserJson(json)
            for(s in servicos){
                saveOffline(s)
            }
            return servicos
        } else {
            val dao = DatabaseManager.getServicoDAO()
            val servicos = dao.findAll()
            return servicos
        }
    }
    fun save(servico: Servico): Response {
        val json = HttpHelper.post("$host/apiservico/", servico.toJson())
        return parserJson<Response>(json)
    }
    fun delete(servico: Servico): Response {
        if (AndroidUtils.isInternetDisponivel(BarberApplication.getInstance().applicationContext)) {
            val url = "$host/apiservico/${servico.id}"
            val json = HttpHelper.delete(url)
            return parserJson(json)
        } else {
            val dao = DatabaseManager.getServicoDAO()
            dao.delete(servico)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

    fun saveOffline(servico: Servico) : Boolean {
        val dao = DatabaseManager.getServicoDAO()
        if (! existeDisciplina(servico)) {
            dao.insert(servico)
        }
        return true
    }

    fun existeDisciplina(servico: Servico): Boolean {
        val dao = DatabaseManager.getServicoDAO()
        return dao.getById(servico.id) != null
    }

}