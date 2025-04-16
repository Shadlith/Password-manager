package com.example.passwordmanager

import androidx.room.*

@Dao
interface PasswordDao {
    @Query("SELECT * FROM passwordentity")
    fun getAll(): List<PasswordEntity>

    @Insert
    fun insert(password: PasswordEntity)

    @Update
    fun update(password: PasswordEntity)

    @Delete
    fun delete(password: PasswordEntity)
}