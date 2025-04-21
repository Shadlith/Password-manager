package com.example.passwordmanager

import androidx.room.*

@Dao
interface PasswordDao {
    @Query("SELECT * FROM passwordentity")
    suspend fun getAll(): List<PasswordEntity>

    @Query("SELECT * FROM passwordentity WHERE id = :primaryKey")
    suspend fun get(primaryKey: Int): PasswordEntity

    @Insert
    suspend fun insert(password: PasswordEntity)

    @Update
    suspend fun update(password: PasswordEntity)

    @Delete
    suspend fun delete(password: PasswordEntity)
}