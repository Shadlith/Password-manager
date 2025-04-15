package com.example.passwordmanager

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity

object AuthUtils {

    private var generatedPassword = ""

    fun logout(context: Context) {
        // Clear user data (shared preferences, sessions, etc.)
        val sharedPref = context.getSharedPreferences("MyAppPrefs[CHANGE THIS]", Context.MODE_PRIVATE)
        sharedPref.edit().clear().apply()

        // Navigate to login screen and clear activity stack
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        context.startActivity(intent)
    }

    fun goBack(activity: Activity) {
        if (activity is ComponentActivity) {
            activity.onBackPressedDispatcher.onBackPressed()
        } else {
            activity.finish()
        }
    }

    fun isValidPassword(password: String): Boolean {
        // This regular expression enforces:
        // - At least one lowercase letter: (?=.*[a-z])
        // - At least one uppercase letter: (?=.*[A-Z])
        // - At least one digit: (?=.*\\d)
        // - At least one special character: (?=.*[@\$!%*?&])
        // - Minimum eight characters: .{8,} **EDIT FOR MORE CHARACTERS OR MADE DYNAMIC FROM SLIDER**
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&]).{8,}\$"
        return Regex(passwordPattern).matches(password)
    }

    fun setGeneratedPassword(password: String) {
        generatedPassword = password
    }

    fun getGeneratedPassword(): String{
        val pw = generatedPassword
        generatedPassword = ""
        return pw
    }

}


