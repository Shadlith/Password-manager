package com.example.passwordmanager

import android.content.Context
import androidx.room.Room

class DBHelper private constructor(context: Context) {

    companion object {
        @Volatile
        private var instance: DBHelper? = null

        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: DBHelper(context).also { instance = it }
            }
    }

    val db = Room.databaseBuilder(
        context,
        PasswordDB::class.java, "password-db"
    ).build()
    val dao = db.passwordDao()

}