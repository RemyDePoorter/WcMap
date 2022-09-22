package com.android.example.wcmap.model.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Entity class, defines a user.
@Entity(tableName = "UserTable")
data class User(

    @PrimaryKey
    var userEmail: String,

    @ColumnInfo(name = "lastConnexion")
    val lastConnexion: Long = System.currentTimeMillis()
)