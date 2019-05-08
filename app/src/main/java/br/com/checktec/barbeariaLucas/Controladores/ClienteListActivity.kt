package br.com.checktec.barbeariaLucas.Controladores

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.checktec.barbeariaLucas.Adapter.ClienteAdapter
import br.com.checktec.barbeariaLucas.Adapter.ServicoAdapter
import br.com.checktec.barbeariaLucas.Models.Cliente
import br.com.checktec.barbeariaLucas.R
import br.com.checktec.barbeariaLucas.Service.ClienteService
import br.com.checktec.barbeariaLucas.Service.ServicoService
import br.com.checktec.barbeariaLucas.Utils.DebugActivity

class ClienteListActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var clientes = listOf<Cliente>()
    var recyclerCliente: RecyclerView? = null
    private var REQUEST_CADASTRO = 1
    private var REQUEST_REMOVE= 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cliente)

        // colocar toolbar
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // alterar título da ActionBar
        supportActionBar?.title = "Clientes"

        // up navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        configuraMenuLateral()

        // configurar cardview
        recyclerCliente = findViewById<RecyclerView>(R.id.recyclerCliente)
        recyclerCliente?.layoutManager = LinearLayoutManager(context)
        recyclerCliente?.itemAnimator = DefaultItemAnimator()
        recyclerCliente?.setHasFixedSize(true)

    }

    override fun onResume() {
        super.onResume()
        // task para recuperar as clientes
        taskCliente()
    }

    fun taskCliente() {
        Thread {
            // Código para procurar as clientes
            // que será executado em segundo plano / Thread separada
            this.clientes = ClienteService.getCliente(context)
            runOnUiThread {
                // Código para atualizar a UI com a lista de clientes
                recyclerCliente?.adapter =
                        ClienteAdapter(clientes) { onClickServicos(it) }

            }
        }.start()
    }

    // tratamento do evento de clicar em uma servico
    fun onClickServicos(cliente: Cliente) {
        Toast.makeText(context, "Clicou servico ${cliente.nome}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, ClienteActivity::class.java)
        intent.putExtra("cliente", cliente)
        //startActivity(intent)
    }

    // configuraçao do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layourMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }


    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_agenda -> {
                Toast.makeText(this, "Clicou agenda", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, TelaInicialActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_cabeleleiro -> {
                Toast.makeText(this, "Clicou cabeleleiro", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, CabeleleiroListActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_clientes -> {
                Toast.makeText(this, "Clicou cliente", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, ClienteListActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_produtos -> {
                Toast.makeText(this, "Clicou produto", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, ProdutoListActivity::class.java)
                startActivity(intent)
            }

            R.id.nav_servicos -> {
                Toast.makeText(this, "Clicou servico", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, ServicoListActivity::class.java)
                startActivity(intent)
            }
        }

        // fecha menu depois de tratar o evento
        val drawer = findViewById<DrawerLayout>(R.id.layourMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun cliqueSair() {
        val returnIntent = Intent();
        returnIntent.putExtra("result","Saída do BrewerApp");
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    // método sobrescrito para inflar o menu na Actionbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // infla o menu com os botões da ActionBar
        menuInflater.inflate(R.menu.menu_main, menu)
        // vincular evento de buscar
        (menu?.findItem(R.id.action_buscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                // ação enquanto está digitando
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                // ação  quando terminou de buscar e enviou
                return false
            }

        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // id do item clicado
        val id = item?.itemId
        // verificar qual item foi clicado e mostrar a mensagem Toast na tela
        // a comparação é feita com o recurso de id definido no xml
        if  (id == R.id.action_buscar) {
            Toast.makeText(context, "Botão de buscar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_atualizar) {
            Toast.makeText(context, "Botão de atualizar", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_config) {
            Toast.makeText(context, "Botão de configuracoes", Toast.LENGTH_LONG).show()
        } else if (id == R.id.action_adicionar){
            Toast.makeText(context, "Botão de adicionar", Toast.LENGTH_LONG).show()
            val intent = Intent(context, ClienteCadastroActivity::class.java)
            startActivityForResult(intent, REQUEST_CADASTRO)
        }
        // botão up navigation
        else if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    // esperar o retorno do cadastro da disciplina
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CADASTRO || requestCode == REQUEST_REMOVE ) {
            // atualizar lista de disciplinas
            taskCliente()
        }
    }


}