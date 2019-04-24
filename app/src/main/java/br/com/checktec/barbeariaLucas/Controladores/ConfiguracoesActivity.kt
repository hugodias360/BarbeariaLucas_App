package br.com.checktec.barbeariaLucas.Controladores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Utils.Host
import kotlinx.android.synthetic.main.activity_configuracoes.*

class ConfiguracoesActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracoes)

        btnConfiguracoes.setOnClickListener {
            Host().host = edtHostConfiguracoes.text.toString()
        }
    }
}