package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

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
        // val xyz = intent.getStringExtra("EXTRA_KEY") RECEIVES DATA FROM ANOTHER ACTIVITY
        val addPasswordButton = findViewById<Button>(R.id.addPasswordActivityAddPasswordButton)
        addPasswordButton.setOnClickListener {
            val intent = Intent(this, PasswordListActivity::class.java)
            startActivity(intent)
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
    }

    override fun onRestart() {
        super.onRestart()
        val passwordBox = findViewById<TextView>(R.id.addPasswordActivityPasswordEditText)
        val newPW = AuthUtils.getGeneratedPassword()
        passwordBox.setText(newPW)
        Toast.makeText(this, newPW, Toast.LENGTH_SHORT).show()
    }
}