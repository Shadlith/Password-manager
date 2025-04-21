package com.example.passwordmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking

class PasswordListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_password_list)

        val masterPassword = intent.getStringExtra("master_password") ?: ""

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dbEntries = runBlocking {
            DBHelper.getInstance(applicationContext, masterPassword).dao.getAll()
        }

        val passwordRecyclerView =
            findViewById<RecyclerView>(R.id.passwordListActivityRecyclerView)
        passwordRecyclerView.layoutManager = LinearLayoutManager(this)
        passwordRecyclerView.adapter = Adapter(dbEntries, this)


        val addPasswordButton = findViewById<Button>(R.id.passwordListActivityAddPasswordButton)
        addPasswordButton.setOnClickListener {
            val intent = Intent(this, AddPasswordActivity::class.java)
            intent.putExtra("master_password", masterPassword)

             // intent.putExtra("EXTRA_KEY", "Some data") PASS DATA TO ANOTHER ACTIVITY

            startActivity(intent)
        }
        val logoutButton = findViewById<Button>(R.id.passwordListActivityLogoutButton)
        logoutButton.setOnClickListener {
            AuthUtils.logout(this)
        }
    }
}