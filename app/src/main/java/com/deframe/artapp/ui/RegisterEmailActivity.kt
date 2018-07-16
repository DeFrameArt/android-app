package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.deframe.artapp.R
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern

/**
 * This class handles the register email screen
 */
class RegisterEmail : AppCompatActivity() {

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.RegisterTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_email)

        //on click listener for next button
        val loginButton = findViewById<TextView>(R.id.btn_next)
        loginButton.setOnClickListener {

            //checks if an email is entered
            if (email.text.toString().isNotBlank() && isEmailValid()) {
                val registerIntent = Intent(this, RegisterActivity::class.java)
                startActivity(registerIntent)
            } else{
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Checks if email if valid
     *
     * @return boolean true for valid false for invalid
     */
    fun isEmailValid(): Boolean {
        var email = email.text.toString()
        val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        return matcher.matches()
    }
}
