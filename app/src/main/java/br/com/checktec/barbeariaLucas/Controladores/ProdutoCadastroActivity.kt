package br.com.checktec.barbeariaLucas.Controladores


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.widget.TextView
import android.widget.Toast
import br.com.checktec.barbeariaLucas.Models.Produto
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Service.ProdutoService
import br.com.checktec.barbeariaLucas.Utils.DebugActivity
import kotlinx.android.synthetic.main.activity_cadastro_produto.*


class ProdutoCadastroActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_produto)

        // colocar toolbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)



        // alterar título da ActionBar
        supportActionBar?.title = "Adicionar Produto"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btnSalvarProduto.setOnClickListener {
            val produto = Produto()
            produto.nome = edtNomeProduto.text.toString()
            produto.especificacao = edtEspecificacaoProduto.text.toString()
            produto.quantidade = edtQuantidadeProduto.text.toString()
            val str = edtValidadeProduto.text.toString()
            val parts = str.split("/")
            val validade = parts[2] + "-" + parts[1] + "-" +parts[0]
            produto.validade_produto = validade
            produto.valor_unitario = edtValorProduto.text.toString()
            taskAtualizar(produto)
        }


    }
    private fun taskAtualizar(produto: Produto) {
// Thread para salvar a disciplina
        Thread {
            ProdutoService.save(produto)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId

        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)

    }
}