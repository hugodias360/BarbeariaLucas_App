package br.com.checktec.barbeariaLucas.Service

import android.content.Context
import android.util.Log
import br.com.checktec.barbeariaLucas.Models.Cliente
import br.com.checktec.barbeariaLucas.Utils.AndroidUtils
import br.com.checktec.barbeariaLucas.Utils.Host
import br.com.checktec.barbeariaLucas.Utils.HttpHelper

import android.widget.Toast
import br.com.checktec.barbeariaLucas.Database.DatabaseManager
import br.com.checktec.barbeariaLucas.Utils.Response
import br.com.fernandosousa.lmsapp.BarberApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object ClienteService {
    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getCliente (context: Context): List<Cliente> {
        var clientes = ArrayList<Cliente>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apicliente"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            clientes = parserJson(json)
            for(c in clientes){
                saveOffline(c)
            }
            return clientes
        } else {
            val dao = DatabaseManager.getClienteDAO()
            val clientes = dao.findAll()
            return clientes
        }
    }
    fun save(cliente: Cliente): Response {
        val json = HttpHelper.post("$host/apicliente/", cliente.toJson())
        Log.d(TAG, json)
        return parserJson<Response>(json)
    }

    fun delete(cliente: Cliente): Response {
        if (AndroidUtils.isInternetDisponivel(BarberApplication.getInstance().applicationContext)) {
            val url = "$host/apicliente/${cliente.id}"
            val json = HttpHelper.delete(url)
            return ServicoService.parserJson(json)
        } else {
            val dao = DatabaseManager.getClienteDAO()
            dao.delete(cliente)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

    fun saveOffline(cliente: Cliente) : Boolean {
        val dao = DatabaseManager.getClienteDAO()
        if (! existeCliente(cliente)) {
            dao.insert(cliente)
        }
        return true
    }

    fun existeCliente(cliente: Cliente): Boolean {
        val dao = DatabaseManager.getClienteDAO()
        return dao.getById(cliente.id) != null
    }
}