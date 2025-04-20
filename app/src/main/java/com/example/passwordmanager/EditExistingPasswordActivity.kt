package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.runBlocking

class EditExistingPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_existing_password)

        val primaryKey = intent.getIntExtra("primary_key", 0)
        val passwordEntry = runBlocking {
            DBHelper.getInstance(applicationContext).dao.get(primaryKey)
        }

        val siteNameTextView = findViewById<TextView>(R.id.editPasswordActivitySiteTextView)
        val usernameEditText = findViewById<EditText>(R.id.editPasswordActivityUsernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.editPasswordActivityPasswordEditText)

        siteNameTextView.setText(passwordEntry.siteName)
        usernameEditText.setText(passwordEntry.username)
        passwordEditText.setText(passwordEntry.password)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val logoutButton = findViewById<Button>(R.id.editPasswordActivityLogoutButton)
        logoutButton.setOnClickListener {
            AuthUtils.logout(this)
        }

        val backButton = findViewById<Button>(R.id.editPasswordActivityBackButton)
        backButton.setOnClickListener {
            AuthUtils.goBack(this)
        }

        val updateButton = findViewById<Button>(R.id.editPasswordActivityUpdateButton)
        updateButton.setOnClickListener {
            runBlocking {
                DBHelper.getInstance(applicationContext).dao.update(PasswordEntity(
                    id = primaryKey,
                    siteName = siteNameTextView.text.toString(),
                    username = usernameEditText.text.toString(),
                    password = passwordEditText.text.toString()
                ))
            }
            AuthUtils.goBack(this)
        }

        val deleteButton = findViewById<Button>(R.id.editPasswordActivityDeleteButton)
        deleteButton.setOnClickListener {
            runBlocking {
                DBHelper.getInstance(applicationContext).dao.delete(passwordEntry)
            }
            val intent = Intent(this, PasswordListActivity::class.java)
            startActivity(intent)
        }
    }
}