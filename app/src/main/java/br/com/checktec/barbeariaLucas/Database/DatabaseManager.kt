package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Room
import br.com.fernandosousa.lmsapp.BarberApplication

object DatabaseManager {
    private var dbInstanceServico: ServicoDatabase
    private var dbInstanceProduto: ProdutoDatabase
    private var dbInstanceAgenda: AgendaDatabase
    private var dbInstanceCabeleleiro : CabeleleiroDatabase
    private var dbInstanceCliente : ClienteDatabase
    init {
        val appContext = BarberApplication.getInstance().applicationContext
        dbInstanceServico = Room.databaseBuilder(
                appContext, // contexto global
                ServicoDatabase::class.java,  // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco

        ).fallbackToDestructiveMigration().build()
        dbInstanceProduto = Room.databaseBuilder(
                appContext, // contexto global
                ProdutoDatabase::class.java,  // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco

        ).fallbackToDestructiveMigration().build()
        dbInstanceAgenda = Room.databaseBuilder(
                appContext, // contexto global
                AgendaDatabase::class.java,  // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco

        ).fallbackToDestructiveMigration().build()
        dbInstanceCabeleleiro = Room.databaseBuilder(
                appContext, // contexto global
                CabeleleiroDatabase::class.java,  // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco

        ).fallbackToDestructiveMigration().build()
        dbInstanceCliente = Room.databaseBuilder(
                appContext, // contexto global
                ClienteDatabase::class.java,  // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco

        ).fallbackToDestructiveMigration().build()
    }
    fun getServicoDAO(): ServicoDAO {
        return dbInstanceServico.servicoDAO()
    }
    fun getProdutoDAO(): ProdutoDAO {
        return dbInstanceProduto.produtoDAO()
    }
    fun getCabeleleiroDAO(): CabeleleiroDAO{
        return dbInstanceCabeleleiro.cabeleleiroDAO()
    }
    fun getAgendaDAO(): AgendaDAO {
        return dbInstanceAgenda.agendaDAO()
    }
    fun getClienteDAO(): ClienteDAO {
        return dbInstanceCliente.clienteDAO()
    }

}