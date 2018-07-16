package com.deframe.artapp.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.widget.TextView
import com.deframe.artapp.R
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import java.util.regex.Pattern


/**
 * This class handles the registration screen
 */
class RegisterActivity : AppCompatActivity() {

    var passwordSecure: Boolean = false
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

            //starts main page only if pw requirements are met. Shows hint otherwise
            if (passwordSecure && password1.text.toString() == email.text.toString() && isNameEntered()) {
                val mainPageIntent = Intent(this, MainActivity::class.java)
                startActivity(mainPageIntent)
            } else if (!(password1.text.toString() == email.text.toString())) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            } else if (!passwordSecure) {
                Toast.makeText(this, "Please use a more secure password", Toast.LENGTH_SHORT).show()
            } else if (!isNameEntered()){
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     *  checks if the user has entered a name in the text fields
     *
     *  @return if user has entered a name
     */
    private fun isNameEntered(): Boolean {
        if (txt_firstname.text.toString().isNotEmpty() && txt_lastname.text.toString().isNotEmpty()) {
            return true
        }
        return false
    }

    /**
     *  Handles actions when a key is pressed. Checks if the password meet the requirements and updates
     *  color of requirements accordingly. Sets passwordSecure to true if all requirements are met
     *
     *  @return if key pressed
     */
    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        //checks
        var containsNum = Pattern.compile("[0-9]").matcher(password1.text).find()
        var containsLowercase = Pattern.compile("[a-z]").matcher(password1.text).find()
        var containsUppercase = Pattern.compile("[A-Z]").matcher(password1.text).find()
        var containsSpCharacter = Pattern.compile("[!@#$%^&*]").matcher(password1.text).find()
        if (containsNum) {
            number.setTextColor(Color.parseColor("green"))
        } else {
            number.setTextColor(Color.parseColor("white"))
        }

        if (containsLowercase && containsUppercase) {
            lowercase.setTextColor(Color.parseColor("green"))
        } else {
            lowercase.setTextColor(Color.parseColor("white"))
        }

        if (containsSpCharacter) {
            spcharacter.setTextColor(Color.parseColor("green"))
        } else {
            spcharacter.setTextColor(Color.parseColor("white"))
        }

        if (password1.length() >= 8) {
            eightcharacters.setTextColor(Color.parseColor("green"))
        } else {
            eightcharacters.setTextColor(Color.parseColor("white"))
        }

        if (containsLowercase && containsUppercase && containsNum && containsSpCharacter && password1.length() >= 8) {
            passwordSecure = true
        }
        return true
    }
}
