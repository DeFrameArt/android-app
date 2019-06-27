package com.deframe.artapp.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.deframe.artapp.R
import com.deframe.artapp.helper.Constants
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONObject
import org.xml.sax.Parser
import java.net.URL
import com.android.volley.AuthFailureError
import com.android.volley.VolleyError
import android.content.SharedPreferences




/**
 * This class handles the login screen
 */
class LoginActivity : AppCompatActivity() {

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    var sp: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sp = getSharedPreferences("login",MODE_PRIVATE)

        if(sp!!.getBoolean("logged",false)){
            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)
        }



        //on click listener for login button
        //TODO: login credentials check has bug
        btn_login.setOnClickListener {
           // checkLoginCredentialsString()

            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)
        }

        //on click listener for switching to register email screen
        val textSignUp = findViewById<TextView>(R.id.txt_signupSwitch)

        textSignUp.setOnClickListener {
            val signUpPageIntent = Intent(this, RegisterEmail::class.java)
            startActivity(signUpPageIntent)
        }

        /*btn_guest_login.setOnClickListener {

        }*/
    }

    /**
     *
     *This method handles the login credential verification of an user
     */
    fun checkLoginCredentialsString() {
        val queue = Volley.newRequestQueue(this)
        val url = URL(Constants.API_TEST + "/accounts/login").toString()
        val loginJSON = JSONObject()

        loginJSON.put("emailAddress", txt_email)
        loginJSON.put("password", txt_password)
        loginJSON.put("role", "Visitor")

        val stringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener { }, Response.ErrorListener { }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                println(headers)
                return if (headers == null) super.getHeaders() else headers
            }

            @Throws(AuthFailureError::class)
            override fun getBody(): ByteArray {
                return loginJSON.toString().toByteArray()
            }
        }
        queue.add(stringRequest)
    }

    /**
     * This method handles the login credential verification of an user
     */
    fun checkLoginCredentials() {
        val queue = Volley.newRequestQueue(this)
        val url = URL(Constants.API_TEST + "/accounts/login").toString()
        val loginJSON = JSONObject()

        loginJSON.put("emailAddress", txt_email)
        loginJSON.put("password", txt_password)
        loginJSON.put("role", "Visitor")

        val request = JsonObjectRequest(Request.Method.POST, url, loginJSON,
                Response.Listener { response ->
                    println("Hey! got Response:")
                    println(response)
                },
                Response.ErrorListener { error ->
                    println("Error occurred")
                    println(error)
                }
        )
        queue.add(request)
    }
}
