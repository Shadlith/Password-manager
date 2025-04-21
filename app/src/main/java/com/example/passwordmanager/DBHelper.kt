package com.example.passwordmanager

import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.runBlocking
import net.zetetic.database.sqlcipher.SQLiteDatabase
import net.zetetic.database.sqlcipher.SupportOpenHelperFactory


class DBHelper private constructor(context: Context, password: String) {

    companion object {
        @Volatile
        private var instance: DBHelper? = null

        fun getInstance(context: Context, password: String) =
            instance ?: synchronized(this) {
                instance ?: DBHelper(context, password).also { instance = it }
            }

        fun correctPassword(context: Context, password: String): Boolean {
            return runBlocking {
                try {
                    DBHelper.getInstance(context, password).dao.getAll()
                    true
                }
                catch (e: Exception) {
                    println(e)
                    instance = null
                    false
                }
            }
        }

    }

    val dao: PasswordDao
    val db: PasswordDB
    init {
        /*
        val databaseFile = context.getDatabasePath("password-db.db")
        if (databaseFile.exists()) {
            databaseFile.delete()
        }
        */
        System.loadLibrary("sqlcipher")
        val passphrase = password.toByteArray()
        val factory = SupportOpenHelperFactory(passphrase)

        db = Room.databaseBuilder(
            context,
            PasswordDB::class.java,
            "password-db.db"
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
        e?.also {
            instance = null
            throw it
        }
    }

}