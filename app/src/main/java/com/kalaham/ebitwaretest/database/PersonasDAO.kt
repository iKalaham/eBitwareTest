package com.kalaham.ebitwaretest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PersonasDAO {

    @Query("SELECT * FROM personasTable")
    fun getAll(): List<PersonasEntidad>

    @Insert
    fun insert(persona: PersonasEntidad)

}