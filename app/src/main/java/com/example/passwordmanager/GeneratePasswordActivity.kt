package com.example.passwordmanager

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.security.SecureRandom

class GeneratePasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_generate_password)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        /* // CHECKS TO SEE IF PASSWORD MEETS MINIMUM CRITERIA - NEEDS WORK (by Dasha)

    val editTextPassword = findViewById<EditText>(R.id.generatePasswordActivityGeneratePasswordEditText)
    editTextPassword.addTextChangedListener(object : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        val password = s.toString()

        // Check each criterion:
        // 1. Minimum length
        val isLengthValid = password.length >= 8
        iconLength.setImageResource(if (isLengthValid) R.drawable.ic_check else R.drawable.ic_cross)

        // 2. At least one uppercase letter
        val isUpperValid = password.any { it.isUpperCase() }
        iconUpperCase.setImageResource(if (isUpperValid) R.drawable.ic_check else R.drawable.ic_cross)

        // 3. At least one lowercase letter
        val isLowerValid = password.any { it.isLowerCase() }
        iconLowerCase.setImageResource(if (isLowerValid) R.drawable.ic_check else R.drawable.ic_cross)

        // 4. At least one digit
        val isDigitValid = password.any { it.isDigit() }
        iconNumber.setImageResource(if (isDigitValid) R.drawable.ic_check else R.drawable.ic_cross)

        // 5. At least one symbol (example: @, $, !, %, *, ?, &)
        val isSymbolValid = password.any { it in "@\$!%*?&" }
        iconSymbol.setImageResource(if (isSymbolValid) R.drawable.ic_check else R.drawable.ic_cross)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
    */
        val logoutButton = findViewById<Button>(R.id.generatePasswordActivityLogoutButton)
        logoutButton.setOnClickListener {
            AuthUtils.logout(this)
        }

        val backButton = findViewById<Button>(R.id.generatePasswordActivityBackButton)
        backButton.setOnClickListener {
            AuthUtils.goBack(this)
        }

        val generateButton = findViewById<Button>(R.id.generatePasswordActivityGeneratePasswordButton)
        val charRequirement = findViewById<TextView>(R.id.CharRequirementReadout)
        val uppercaseRequired = findViewById<CheckBox>(R.id.checkBoxUpperCaseRequirement)
        val numbersRequired = findViewById<CheckBox>(R.id.checkBoxNumberCaseRequirement)
        val specialCharactersRequiredRequired = findViewById<CheckBox>(R.id.checkBoxSpecialCharacterRequirement)
        generateButton.setOnClickListener{
            val passwordLength = charRequirement.text.toString().toInt()
            if(passwordLength > 4) {
                val newPassword = generatePassword(passwordLength, uppercaseRequired.isChecked, numbersRequired.isChecked, specialCharactersRequiredRequired.isChecked)
                val generatedPassword =
                    findViewById<TextView>(R.id.generatePasswordActivityGeneratePasswordEditText)
                generatedPassword.setText(newPassword)
            }
            else{
                Toast.makeText(this, "Password too short, try 5 characters or longer.", Toast.LENGTH_SHORT).show()
            }

        }


    }

    private fun generatePassword(passwordLength: Int, uppercaseRequired: Boolean, numbersRequired: Boolean, specialCharactersRequired: Boolean): String {
        val generator = PasswordGenerator()
        var password = generator.generatePassword(passwordLength, uppercaseRequired, numbersRequired, specialCharactersRequired)
        return password.toString()
    }


}