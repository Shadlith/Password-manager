package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.runBlocking

class AddPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val siteNameEditText = findViewById<EditText>(R.id.addPasswordActivitySiteUrlEditText)
        val usernameEditText = findViewById<EditText>(R.id.addPasswordActivityUsernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.addPasswordActivityPasswordEditText)

        // val xyz = intent.getStringExtra("EXTRA_KEY") RECEIVES DATA FROM ANOTHER ACTIVITY
        val addPasswordButton = findViewById<Button>(R.id.addPasswordActivityAddPasswordButton)
        addPasswordButton.setOnClickListener {

            var violation = false
            if(siteNameEditText.text.toString() == "") {
                siteNameEditText.setError("No Site Provided!")
                violation = true
            }
            if(usernameEditText.text.toString() == "") {
                usernameEditText.setError("No Username Provided!")
                violation = true
            }
            if(passwordEditText.text.toString() == "") {
                passwordEditText.setError("No Site Provided!")
                violation = true
            }

            if(!violation) {
                runBlocking {
                    DBHelper.getInstance(applicationContext).dao.insert(
                        PasswordEntity(
                            siteName = siteNameEditText.text.toString(),
                            username = usernameEditText.text.toString(),
                            password = passwordEditText.text.toString()
                        )
                    )
                }
                val intent = Intent(this, PasswordListActivity::class.java)
                startActivity(intent)
            }
        }
        val generatePasswordButton = findViewById<Button>(R.id.addPasswordActivityGeneratePasswordButton)
        generatePasswordButton.setOnClickListener {
            val intent = Intent(this, GeneratePasswordActivity::class.java)
            startActivity(intent)
        }

        val logoutButton = findViewById<Button>(R.id.addPasswordActivityLogoutButton)
        logoutButton.setOnClickListener {
            AuthUtils.logout(this)
        }

        val backButton = findViewById<Button>(R.id.addPasswordActivityBackButton)
        backButton.setOnClickListener {
            AuthUtils.goBack(this)
        }

        val clearButton = findViewById<Button>(R.id.addPasswordActivityClearButton)
        clearButton.setOnClickListener {
            siteNameEditText.setText("")
            usernameEditText.setText("")
            passwordEditText.setText("")
        }
    }

    override fun onRestart() {
        super.onRestart()
        val passwordBox = findViewById<TextView>(R.id.addPasswordActivityPasswordEditText)
        val newPW = AuthUtils.getGeneratedPassword()
        passwordBox.setText(newPW)
        Toast.makeText(this, newPW, Toast.LENGTH_SHORT).show()
    }
}