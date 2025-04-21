package com.example.passwordmanager

import androidx.room.*

@Entity
data class PasswordEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "site_name") val siteName: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String
)