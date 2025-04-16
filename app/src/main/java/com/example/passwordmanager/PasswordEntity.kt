package com.example.passwordmanager

import androidx.room.*

@Entity
data class PasswordEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "site_name") val siteName: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String
)