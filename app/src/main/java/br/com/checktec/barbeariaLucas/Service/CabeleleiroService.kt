package br.com.checktec.barbeariaLucas.Service

import android.content.Context
import android.util.Log
import br.com.checktec.barbeariaLucas.Database.DatabaseManager
import br.com.checktec.barbeariaLucas.Models.Cabeleleiro
import br.com.checktec.barbeariaLucas.Utils.AndroidUtils
import br.com.checktec.barbeariaLucas.Utils.Host
import br.com.checktec.barbeariaLucas.Utils.HttpHelper
import br.com.checktec.barbeariaLucas.Utils.Response
import br.com.fernandosousa.lmsapp.BarberApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CabeleleiroService {
    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getCabeleleiro (context: Context): List<Cabeleleiro> {
        var cabeleleiros = ArrayList<Cabeleleiro>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apicabeleleiros"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            cabeleleiros = parserJson(json)
            for(c in cabeleleiros){
                saveOffline(c)
            }
            return cabeleleiros
        } else {
            val dao = DatabaseManager.getCabeleleiroDAO()
            val cabeleleiros = dao.findAll()
            return cabeleleiros
        }
    }
    fun save(cabeleleiro: Cabeleleiro): Response {
        val json = HttpHelper.post("$host/apicabeleleiros/", cabeleleiro.toJson())
        Log.d(TAG, json)
        return parserJson<Response>(json)
    }

    fun delete(cabeleleiro: Cabeleleiro): Response {
        if (AndroidUtils.isInternetDisponivel(BarberApplication.getInstance().applicationContext)) {
            val url = "$host/apicabeleleiros/${cabeleleiro.id}"
            val json = HttpHelper.delete(url)
            return parserJson(json)
        } else {
            val dao = DatabaseManager.getCabeleleiroDAO()
            dao.delete(cabeleleiro)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

    fun saveOffline(cabeleleiro: Cabeleleiro) : Boolean {
        val dao = DatabaseManager.getCabeleleiroDAO()
        if (! existeCabeleleiro(cabeleleiro)) {
            dao.insert(cabeleleiro)
        }
        return true
    }

    fun existeCabeleleiro(cabeleleiro: Cabeleleiro): Boolean {
        val dao = DatabaseManager.getCabeleleiroDAO()
        return dao.getById(cabeleleiro.id) != null
    }
}