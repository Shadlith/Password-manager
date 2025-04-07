package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PasswordListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password_list)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val addPasswordButton = findViewById<Button>(R.id.passwordListActivityAddPasswordButton)
        addPasswordButton.setOnClickListener {
            val intent = Intent(this, AddPasswordActivity::class.java)

             // intent.putExtra("EXTRA_KEY", "Some data") PASS DATA TO ANOTHER ACTIVITY

            startActivity(intent)
        }
        val logoutButton = findViewById<Button>(R.id.passwordListActivityLogoutButton)
        logoutButton.setOnClickListener {
            AuthUtils.logout(this)
        }
    }
}