package com.android.example.wcmap.model.user

import androidx.lifecycle.LiveData
import androidx.room.*

// Data Access Objects interface.
// Allows you to define the methods that will execute SQL operations in the DB.
@Dao
interface UserDao {

    // Get one user with him email and lastConnexion date.
    @Query("SELECT * from UserTable WHERE userEmail = :key")
    fun get(key: String): User?

    // Get all users emails.
    @Query("SELECT userEmail from UserTable ORDER BY userEmail ASC")
    fun getAll(): LiveData<List<String>>

    // Insert new user with userEmail and set him date with system date.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    // Update userEmail by set date with the new system date.
    @Update
    fun update(user: User)

    // Clear UserTable.
    @Query("DELETE FROM UserTable")
    fun clear()
}