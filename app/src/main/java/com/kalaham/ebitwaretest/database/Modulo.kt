package com.kalaham.ebitwaretest.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PersonasEntidad::class], version = 1, exportSchema = false
)
abstract class Modulo : RoomDatabase() {

    abstract fun personasDAO(): PersonasDAO

}