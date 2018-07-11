package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.deframe.artapp.R
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject
import org.xml.sax.Parser
import java.net.URL

/**
 * This class handles the login screen
 */
class LoginActivity : AppCompatActivity() {

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //on click listener for login button
        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)
        }

        //on click listener for switching to register email screen
        val textSignUp = findViewById<TextView>(R.id.txt_signupSwitch)

        textSignUp.setOnClickListener {
            val signUpPageIntent = Intent(this, RegisterEmail::class.java)
            startActivity(signUpPageIntent)
        }

        btn_guest_login.setOnClickListener {
            val guestPageIntent = Intent(this, RegisterGuestActivity::class.java)
            startActivity(guestPageIntent)
        }
    }
}
