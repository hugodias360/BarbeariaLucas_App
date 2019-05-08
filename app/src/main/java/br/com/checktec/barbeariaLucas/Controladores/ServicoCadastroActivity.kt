package br.com.checktec.barbeariaLucas.Controladores

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.checktec.barbeariaLucas.Models.Servico
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Service.ServicoService
import kotlinx.android.synthetic.main.activity_cadastro_servico.*


class ServicoCadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_servico)
        setTitle("Novo Servico")
        btnSalvarServico.setOnClickListener {
            val servico = Servico()
            servico.nome = edtNomeServico.text.toString()
            servico.valor = edtValorServico.text.toString()
            taskAtualizar(servico)
        }
    }
    private fun taskAtualizar(servico: Servico) {
// Thread para salvar a disciplina
        Thread {
            ServicoService
                    .save(servico)
            runOnUiThread {
                // após cadastrar, voltar para activity anterior
                finish()
            }
        }.start()
    }
}