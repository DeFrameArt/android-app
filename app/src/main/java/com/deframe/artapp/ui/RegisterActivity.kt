package com.deframe.artapp.ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.widget.TextView
import com.deframe.artapp.R
import android.widget.Toast
import com.android.volley.Request
import com.deframe.artapp.R.id.password1
import com.deframe.artapp.helper.Constants
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Pattern
import com.android.volley.VolleyError
import com.android.volley.Request.Method.POST
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.deframe.artapp.helper.User
import kotlinx.android.synthetic.main.activity_register.view.*

/**
 * This class handles the registration screen
 */
class RegisterActivity : AppCompatActivity() {
    private var email = ""

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

        //get email address of user
        val intent = intent
        email = intent.getStringExtra("email")

        //on click listener for register button
        val registerButton = findViewById<TextView>(R.id.btn_register)
        registerButton.setOnClickListener {

            //starts main page only if pw requirements are met. Shows hint otherwise
            if (passwordSecure && password1.text.toString() == pawssword2.text.toString() && isNameEntered()) {
                recordNewUser()
                setCurrentUser(txt_firstname.text.toString(), txt_lastname.text.toString(), email)
                val mainPageIntent = Intent(this, MainActivity::class.java)
                startActivity(mainPageIntent)
            } else if (!(password1.text.toString() == pawssword2.text.toString())) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            } else if (!passwordSecure) {
                Toast.makeText(this, "Please use a more secure password", Toast.LENGTH_SHORT).show()
            } else if (!isNameEntered()) {
                Toast.makeText(this, "Please enter name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setCurrentUser(first: String, last: String, email: String) {
        User.firstname = first
        User.lastname = last
        User.email = email
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

    /**
     * This method creates a JSON object according to the parameters
     *
     * @param email
     * @param first
     * @param last
     * @param pw
     * @return JSON Object that contains the basic info of the new user
     */
    fun makeUserJSON(email: String, first: String, last: String, pw: String): JSONObject {
        val newUserJSON = JSONObject()
        newUserJSON.put("emailAddress", email)
        newUserJSON.put("firstName", first)
        newUserJSON.put("lastName", last)
        newUserJSON.put("password", pw)
        newUserJSON.put("role", "Visitor")

        return newUserJSON
    }

    /**
     * This method handles the post request of a new user
     */
    fun recordNewUser() {

        val queue = Volley.newRequestQueue(this)
        val url = URL(Constants.API_TEST + "/users/add").toString()

        val newUserJSON = makeUserJSON(email,
                txt_firstname.text.toString(),
                txt_lastname.text.toString(),
                password1.text.toString())

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, newUserJSON,
                Response.Listener { response ->
                    println("Response: %s".format(response.toString()))
                },
                Response.ErrorListener { error ->
                    println(error)
                }
        )
        queue.add(jsonObjectRequest)
    }
}
