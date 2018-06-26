package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.deframe.artapp.R
import org.json.JSONArray
import org.json.JSONObject
import org.xml.sax.Parser
import java.net.URL


class LoginActivity : AppCompatActivity() {

    /**
     *
     * @param savedInstanceState
     * @return Unit
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //on click listener for login button
        val loginButton = findViewById<Button>(R.id.btn_login)
        loginButton.setOnClickListener {
            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)
        }

        //on click listener for switching to sign up
        val textSignUp = findViewById<TextView>(R.id.txt_signupSwitch)

        textSignUp.setOnClickListener {
            val signUpPageIntent = Intent(this,RegisterActivity::class.java)
            startActivity(signUpPageIntent)
        }

        //Get JSON from url



        //Test JSON


     /*   val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject
        println("Time : ${json.string("time")}, Since epoch : ${json.long("milliseconds_since_epoch")}, Date : ${json.string("date")}, ")*/

    }




}
