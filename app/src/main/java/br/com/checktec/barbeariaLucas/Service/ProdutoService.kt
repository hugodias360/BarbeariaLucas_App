package br.com.checktec.barbeariaLucas.Service

import android.content.Context
import android.util.Log
import br.com.checktec.barbeariaLucas.Database.DatabaseManager
import br.com.checktec.barbeariaLucas.Models.Produto
import br.com.checktec.barbeariaLucas.Utils.AndroidUtils
import br.com.checktec.barbeariaLucas.Utils.Host
import br.com.checktec.barbeariaLucas.Utils.HttpHelper
import br.com.checktec.barbeariaLucas.Utils.Response
import br.com.fernandosousa.lmsapp.BarberApplication
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProdutoService {
    val host = Host().host
    val TAG = "WS_LMSApp"

    fun getProduto (context: Context): List<Produto> {
        var produtos = ArrayList<Produto>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/apiproduto"
            val json = HttpHelper.get(url)
            Log.d(TAG, json)
            produtos = parserJson(json)
            for(c in produtos){
                saveOffline(c)
            }
            return produtos
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            val produtos = dao.findAll()
            return produtos
        }
    }
    fun save(produto: Produto): Response {
        val json = HttpHelper.post("$host/apiproduto/", produto.toJson())
        Log.d(TAG, json)
        return parserJson<Response>(json)
    }

    fun delete(produto: Produto): Response {
        if (AndroidUtils.isInternetDisponivel(BarberApplication.getInstance().applicationContext)) {
            val url = "$host/apiproduto/${produto.id}"
            val json = HttpHelper.delete(url)
            return ServicoService.parserJson(json)
        } else {
            val dao = DatabaseManager.getProdutoDAO()
            dao.delete(produto)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }

    fun saveOffline(produto: Produto) : Boolean {
        val dao = DatabaseManager.getProdutoDAO()
        if (! existeProduto(produto)) {
            dao.insert(produto)
        }
        return true
    }

    fun existeProduto(produto: Produto): Boolean {
        val dao = DatabaseManager.getProdutoDAO()
        return dao.getById(produto.id) != null
    }
}