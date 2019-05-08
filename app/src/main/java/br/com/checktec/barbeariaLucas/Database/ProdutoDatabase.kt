package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.checktec.barbeariaLucas.Models.Produto


@Database(entities = arrayOf(Produto::class), version = 4)
abstract class ProdutoDatabase : RoomDatabase() {
    abstract fun produtoDAO(): ProdutoDAO
}