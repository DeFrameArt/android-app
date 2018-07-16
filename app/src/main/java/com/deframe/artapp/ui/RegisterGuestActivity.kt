package com.deframe.artapp.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deframe.artapp.R
import kotlinx.android.synthetic.main.activity_guest_register.*

/**
 * This class handles the guest registration screen
 */
class RegisterGuestActivity : AppCompatActivity() {

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_register)

        //login button opens main page
        guest_login.setOnClickListener {
            val mainPageIntent = Intent(this, MainActivity::class.java)
            startActivity(mainPageIntent)
        }
    }


}
