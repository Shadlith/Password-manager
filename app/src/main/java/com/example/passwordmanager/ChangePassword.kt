package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ChangePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_change_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val currentPasswordEditText = findViewById<EditText>(R.id.changePasswordEditTextCurrentPassword)
        val newPasswordEditText = findViewById<EditText>(R.id.changePasswordEditTextNewPassword)

        val confirmChangePassword = findViewById<Button>(R.id.changePasswordConfirmButton)
        confirmChangePassword.setOnClickListener {
            var violation = false

            if(newPasswordEditText.text.toString() == "") {
                newPasswordEditText.setError("No Password Given")
                violation = true
            }
            if(currentPasswordEditText.text.toString() == "") {
                currentPasswordEditText.setError("No Password Given")
                violation = true
            }
            else if(!violation && !DBHelper.correctPassword(applicationContext, currentPasswordEditText.text.toString())) {
                currentPasswordEditText.setError("Incorrect Password Given")
                violation = true
            }

            if(!violation) {
                val newPassword: String = newPasswordEditText.text.toString()
                val query = "PRAGMA rekey = \'?\';"
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val db = DBHelper.getInstance(
                            applicationContext,
                            currentPasswordEditText.text.toString()
                        ).db
                        db.query(query, arrayOf(newPassword))
                    }
                    catch (e: Exception) {
                        println(e)
                    }
                }
                currentPasswordEditText.setText("")
                newPasswordEditText.setText("")
            }
        }

        val backButton = findViewById<Button>(R.id.changePasswordButtonBack)
        backButton.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}