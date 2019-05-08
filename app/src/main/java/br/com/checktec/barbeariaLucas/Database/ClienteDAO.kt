package br.com.checktec.barbeariaLucas.Database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import br.com.checktec.barbeariaLucas.Models.Cliente

@Dao
interface ClienteDAO {
    @Query("SELECT * FROM cliente where id = :id")
    fun getById(id: Long) : Cliente?
    @Query("SELECT * FROM cliente")
    fun findAll(): List<Cliente>
    @Insert
    fun insert(cliente: Cliente)
    @Delete
    fun delete(cliente: Cliente)
}