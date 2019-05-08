package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.checktec.barbeariaLucas.Models.Cliente

@Database(entities = arrayOf(Cliente::class), version = 3)
abstract class ClienteDatabase : RoomDatabase() {
    abstract fun clienteDAO(): ClienteDAO
}