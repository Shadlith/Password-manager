package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLoginPageUsernameTextView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loginButton = findViewById<Button>(R.id.mainActivityLoginButton)
        val passwordEditText = findViewById<EditText>(R.id.mainActivityPasswordEditText)

        loginButton.setOnClickListener {

            val correctPassword: Boolean = runBlocking {
                try {
                    DBHelper.getInstance(applicationContext, passwordEditText.text.toString()).dao.getAll()
                    true
                }
                catch (e: Exception) {
                    false
                }
            }

            if(correctPassword) {
                val intent = Intent(this, PasswordListActivity::class.java)
                intent.putExtra("master_password", passwordEditText.text.toString())
                startActivity(intent)
            }
        }
    }
}