package com.example.passwordmanager

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PasswordEntity::class], version = 1)
abstract class PasswordDB : RoomDatabase() {
    abstract fun passwordDao(): PasswordDao
}