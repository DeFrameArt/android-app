package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.deframe.artapp.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)

        }

        val textSignUp = findViewById<TextView>(R.id.txt_signupSwitch)
        textSignUp.setOnClickListener {
            val signUpPageIntent = Intent(this,RegisterActivity::class.java)
            startActivity(signUpPageIntent)
        }



    }


}
