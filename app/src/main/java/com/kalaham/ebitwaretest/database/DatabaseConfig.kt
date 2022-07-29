package com.kalaham.ebitwaretest.database

import android.content.Context
import androidx.room.Room

object DatabaseConfig {

    private const val DB_NAME = "DATABASE_PERSONAS"
    private var mInstance: Modulo? = null

    fun getInstance(context: Context): Modulo {
        if (mInstance == null) {
            synchronized(DatabaseConfig::class) {
                if (mInstance != null) mInstance = null
                mInstance = buildDatabase(context)
            }
        }
        return mInstance!!
    }

    private fun buildDatabase(context: Context): Modulo {
        return Room.databaseBuilder(context, Modulo::class.java, DB_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}