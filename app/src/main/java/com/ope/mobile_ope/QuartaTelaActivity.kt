package com.ope.mobile_ope


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_tela_inicial.*

class QuartaTelaActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quarta_tela)


        // colocar toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val args = intent.extras
        // recuperar o parâmetro do tipo String
        val nome_bar = args.getString("titulo")

        val mensagem = findViewById<TextView>(R.id.mensagemInicial)
        mensagem.text = nome_bar


        // alterar título da ActionBar
        supportActionBar?.title = nome_bar
        supportActionBar?.setHomeButtonEnabled(true)    //Ativar o botão
        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId


        when (item?.getItemId()) {
            android.R.id.home -> {
                val homeIntent = Intent(this, TelaInicialActivity::class.java)
                startActivity(homeIntent)
            }
        }
        return super.onOptionsItemSelected(item)

    }
}