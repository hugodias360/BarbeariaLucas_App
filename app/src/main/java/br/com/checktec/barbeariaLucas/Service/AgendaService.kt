package br.com.checktec.barbeariaLucas.Service

import android.content.Context
import android.util.Log
import br.com.checktec.barbeariaLucas.Database.DatabaseManager
import br.com.checktec.barbeariaLucas.Models.Agenda
import br.com.checktec.barbeariaLucas.Utils.AndroidUtils
import br.com.checktec.barbeariaLucas.Utils.Host
import br.com.checktec.barbeariaLucas.Utils.HttpHelper
import br.com.checktec.barbeariaLucas.Utils.Response
import br.com.fernandosousa.lmsapp.BarberApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object AgendaService {
    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getAgenda (context: Context): List<Agenda> {
        var agendas = ArrayList<Agenda>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apiagendamento"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            agendas = parserJson(json)
            for(c in agendas){
                saveOffline(c)
            }
            return agendas
        } else {
            val dao = DatabaseManager.getAgendaDAO()
            val agendas = dao.findAll()
            return agendas
        }
    }
    fun save(agenda: Agenda): Response {
        val json = HttpHelper.post("$host/apiagendamento/", agenda.toJson())
        Log.d(TAG, json)
        return parserJson<Response>(json)
    }

    fun delete(agenda: Agenda): Response {
        if (AndroidUtils.isInternetDisponivel(BarberApplication.getInstance().applicationContext)) {
            val url = "$host/apiagendamento/${agenda.id}"
            val json = HttpHelper.delete(url)
            return parserJson(json)
        } else {
            val dao = DatabaseManager.getAgendaDAO()
            dao.delete(agenda)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

    fun saveOffline(agenda: Agenda) : Boolean {
        val dao = DatabaseManager.getAgendaDAO()
        if (! existeAgenda(agenda)) {
            dao.insert(agenda)
        }
        return true
    }

    fun existeAgenda(agenda: Agenda): Boolean {
        val dao = DatabaseManager.getAgendaDAO()
        return dao.getById(agenda.id) != null
    }
}