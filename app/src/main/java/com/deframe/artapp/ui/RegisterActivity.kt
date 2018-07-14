package com.deframe.artapp.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import android.view.KeyEvent
import android.widget.Button
import android.widget.TextView
import com.deframe.artapp.R
import android.view.KeyEvent.KEYCODE_K
import android.view.KeyEvent.KEYCODE_J
import android.view.KeyEvent.KEYCODE_F
import android.view.KeyEvent.KEYCODE_D
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern


/**
 * This class handles the registration screen
 */
class RegisterActivity : AppCompatActivity() {
    /**
     *
     * @param savedInstanceState
     * @return Unit
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.RegisterTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //on click listener for register button
        val registerButton = findViewById<TextView>(R.id.btn_register)
        registerButton.setOnClickListener {
            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        //checks
        var containsNum = Pattern.compile("[0-9]").matcher( password1.text).find()
        var containsLowercase = Pattern.compile("[a-z]").matcher( password1.text).find()
        var containsUppercase = Pattern.compile("[A-Z]").matcher( password1.text).find()
        var containsSpCharacter = Pattern.compile("[!@#$%^&*]").matcher( password1.text).find()
        println(password1.text)
        println(containsNum)
        println(containsLowercase)
        println(containsUppercase)
        println(containsSpCharacter)

        if(containsNum) {
            number.setTextColor(Color.parseColor("green"))
        } else {
            number.setTextColor(Color.parseColor("white"))
        }

        if(containsLowercase && containsUppercase) {
            lowercase.setTextColor(Color.parseColor("green"))
        } else {
            lowercase.setTextColor(Color.parseColor("white"))
        }

        if(containsSpCharacter) {
            spcharacter.setTextColor(Color.parseColor("green"))
        } else {
            spcharacter.setTextColor(Color.parseColor("white"))
        }
        return true
    }
}
