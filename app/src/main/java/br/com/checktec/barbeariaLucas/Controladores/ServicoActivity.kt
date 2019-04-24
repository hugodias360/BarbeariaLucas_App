package br.com.checktec.barbeariaLucas.Controladores

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.TextView
import br.com.checktec.barbeariaLucas.Models.Servico
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Utils.DebugActivity

class ServicoActivity : DebugActivity() {

    var servico: Servico? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servico)

        // recuperar onjeto de Servico da Intent
        servico = intent.getSerializableExtra("servico") as Servico

        // configurar título com nome da Servico e botão de voltar da Toobar
        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = servico?.nome

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // atualizar dados do carro
        var texto = findViewById<TextView>(R.id.nomeDisciplina)
        texto.text = servico?.nome
        var imagem = findViewById<ImageView>(R.id.imagemDisciplina)
/*        Picasso.with(this).load(servico?.foto).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}

                    override fun onError() { }
                })
                */
    }
}
