package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.checktec.barbeariaLucas.Models.Servico

@Database(entities = arrayOf(Servico::class), version = 5)
abstract class ServicoDatabase : RoomDatabase() {
    abstract fun servicoDAO(): ServicoDAO
}