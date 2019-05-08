package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import br.com.checktec.barbeariaLucas.Models.Agenda

@Database(entities = arrayOf(Agenda::class), version = 2)
abstract class AgendaDatabase : RoomDatabase(){
    abstract fun agendaDAO(): AgendaDAO
}