package br.com.checktec.barbeariaLucas.Controladores

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Utils.DebugActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // encontra objeto pelo id
        val imagem = findViewById<ImageView>(R.id.campo_imagem)
        imagem.setImageResource(R.drawable.login)

        val texto = findViewById<TextView>(R.id.texto_login)
        texto.text = getString(R.string.mensagem_login)


        val botaoLogin = findViewById<Button>(R.id.botao_login)

        botaoLogin.setOnClickListener {onClickLogin() }

    }

    fun onClickLogin(){

        val valorUsuario = campo_usuario.text.toString()
        val valorSenha = campo_senha.text.toString()

        if(valorUsuario == "hugo" && valorSenha == "1234") {
            // criar intent
            val intent = Intent(context, TelaInicialActivity::class.java)
            // colocar parâmetros (opcional)
            val params = Bundle()
            params.putString("nome", valorUsuario)

            intent.putExtras(params)

            // fazer a chamada esperando resultado
            startActivityForResult(intent, 1)
        }else{
            Toast.makeText(context, "Usúario ou senha invalidos!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            val result = data?.getStringExtra("result")
            Toast.makeText(context, "$result", Toast.LENGTH_LONG).show()
        }
    }
}
