package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.checktec.barbeariaLucas.Models.Cabeleleiro

@Database(entities = arrayOf(Cabeleleiro::class), version = 1)
abstract class CabeleleiroDatabase : RoomDatabase(){
    abstract fun cabeleleiroDAO():CabeleleiroDAO
}