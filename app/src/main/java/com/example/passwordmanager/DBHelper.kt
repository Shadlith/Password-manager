package com.example.passwordmanager

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.runBlocking
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory

class DBHelper private constructor(context: Context, password: String) {

    companion object {
        @Volatile
        private var instance: DBHelper? = null

        fun getInstance(context: Context, password: String) =
            instance ?: synchronized(this) {
                instance ?: DBHelper(context, password).also { instance = it }
            }
    }

    var dao: PasswordDao
    init {
        System.loadLibrary("sqlcipher")
        val databaseFile = context.getDatabasePath("password-db.db");
        val passphrase = password.toByteArray()
        val factory = SupportOpenHelperFactory(passphrase)

        val db = Room.databaseBuilder(
            context,
            PasswordDB::class.java,
            "password-db"
        ).openHelperFactory(factory).build()

        dao = db.passwordDao()

        var e: Exception? = runBlocking {
            var temp:Exception? = null
            try { dao.getAll() }
            catch (e: Exception) {
                temp = e
            }
            temp
        }
        e?.also { throw it }
    }



}