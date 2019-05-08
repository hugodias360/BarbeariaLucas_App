package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.checktec.barbeariaLucas.Models.Cabeleleiro

@Dao
interface CabeleleiroDAO {
    @Query("SELECT * FROM cabeleleiro where id = :id")
    fun getById(id: Long) : Cabeleleiro?
    @Query("SELECT * FROM cabeleleiro")
    fun findAll(): List<Cabeleleiro>
    @Insert
    fun insert(cabeleleiro: Cabeleleiro)
    @Delete
    fun delete(cabeleleiro: Cabeleleiro)
}