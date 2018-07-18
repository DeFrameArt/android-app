package com.deframe.artapp.ui

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.deframe.artapp.R
import com.deframe.artapp.R.color.white
import com.deframe.artapp.helper.User
import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_guest_register.*

/**
 * This class handles the guest registration screen
 */
class RegisterGuestActivity : AppCompatActivity() {
    private var currentUserPic: CircularImageView? = null

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_register)

        //login button opens main page and checks if name entered
        guest_login.setOnClickListener {
            if(guest_name.text.toString().isEmpty()){
                Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                //if no image selected, goes for the first one
                if(currentUserPic == null) {
                    currentUserPic = userpic1
                }
                //sets the user singleton object's info
                User.profilepic = currentUserPic!!.drawable
                User.firstname = guest_name.text.toString()

                val mainPageIntent = Intent(this, MainActivity::class.java)
                startActivity(mainPageIntent)
            }
        }

        //on click listeners for user profile pictures
        userpic1.setOnClickListener {
            handlesClick(userpic1)
        }
        userpic2.setOnClickListener {
            handlesClick(userpic2)
        }
        userpic3.setOnClickListener {
            handlesClick(userpic3)
        }
        userpic4.setOnClickListener {
            handlesClick(userpic4)
        }
        userpic5.setOnClickListener {
            handlesClick(userpic5)
        }
        userpic5.setOnClickListener {
            handlesClick(userpic5)
        }
        userpic6.setOnClickListener {
            handlesClick(userpic6)
        }
        userpic7.setOnClickListener {
            handlesClick(userpic7)
        }
        userpic8.setOnClickListener {
            handlesClick(userpic8)
        }
        userpic9.setOnClickListener {
            handlesClick(userpic9)
        }
    }

    /**
     * This function changes the background of user pic when clicked & sets the previous one's background
     * back to white
     *
     * @param img the selected user picture
     */
    private fun handlesClick(img: CircularImageView?): Unit {
        if (currentUserPic != null) {
            currentUserPic!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
            currentUserPic = img
            img!!.setBackgroundColor((Color.parseColor("#D1427A")))
        } else {
            currentUserPic = img
            img!!.setBackgroundColor((Color.parseColor("#D1427A")))
        }
    }
}
