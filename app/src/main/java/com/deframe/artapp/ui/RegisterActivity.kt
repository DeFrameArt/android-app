package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.deframe.artapp.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton = findViewById<Button>(R.id.btn_register)
        registerButton.setOnClickListener {
            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)

        }

        val textLogIn = findViewById<TextView>(R.id.txt_loginSwitch)
        textLogIn.setOnClickListener {
            val logInPageIntent = Intent(this,LoginActivity::class.java)
            startActivity(logInPageIntent)
        }
    }


}
