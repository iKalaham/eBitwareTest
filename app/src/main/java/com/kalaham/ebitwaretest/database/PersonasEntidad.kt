package com.kalaham.ebitwaretest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "personasTable")
data class PersonasEntidad(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,
    val name: String,
    val age: String,
    val nss: String,
    val weight: String,
    val height: String,
    val legal: String,
    val idealWeight: String
)
