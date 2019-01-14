package com.deframe.artapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.deframe.artapp.R
import com.deframe.artapp.helper.User
//import com.mikhaellopez.circularimageview.CircularImageView
import kotlinx.android.synthetic.main.activity_guest_register.*
import kotlinx.android.synthetic.main.activity_painting.view.*
import android.graphics.Bitmap
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream


/**
 * This class handles the guest registration screen
 */
class RegisterGuestActivity : AppCompatActivity() {
    private var currentUserPic: ImageView? = null
    var sp: SharedPreferences? = null
    var user: User = User
    var uri: Uri? = null

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guest_register)
        sp = getSharedPreferences("login",MODE_PRIVATE)

    }

    /**
     * Method to do guest login
     * @param view View object
     */
    fun clickloginGuestBtn(view : View){
        if(guest_name.text.toString().isEmpty()){
            Toast.makeText(this,"Please enter your name", Toast.LENGTH_SHORT).show()
           return
        }
        if(currentUserPic == null) {
            Toast.makeText(this,"Please select a Picture", Toast.LENGTH_SHORT).show()
            return
        }
        val mainPageIntent = Intent(this, MainActivity::class.java)
        user.firstname = guest_name.text.toString()
        sp?.edit()?.putBoolean("logged",true)?.apply()
        sp?.edit()?.putString("name",guest_name.text.toString())?.apply()
        startActivity(mainPageIntent)
    }

    /**
     * Method to do select picture
     * @param view View object
     */
    fun selectUserPic(view : View){
        var img: ImageView? = null
        var resourceId: Int = 0
        when(view.id) {
            R.id.userpic1 -> {
                img = userpic1
                resourceId = R.drawable.userpic1
            }
            R.id.userpic2 -> {
                img = userpic2
                resourceId = R.drawable.userpic2
            }
            R.id.userpic3 -> {
                img = userpic3
                resourceId = R.drawable.userpic3
            }
            R.id.userpic4 -> {
                img = userpic4
                resourceId = R.drawable.userpic4
            }
            R.id.userpic5 -> {
                img = userpic5
                resourceId = R.drawable.userpic5
            }
            R.id.userpic6 -> {
                img = userpic6
                resourceId = R.drawable.userprofile
            }
            R.id.userpic7 -> {
                img = userpic7
                resourceId = R.drawable.userpic7
            }
            R.id.userpic8 -> {
                img = userpic8
                resourceId = R.drawable.userpic8
            }
            R.id.userpic9 -> {
                img = userpic9
                resourceId = R.drawable.userpic9
            }
         }
        handlesClick(img)
        //if no image selected, goes for the first one
        if(currentUserPic == null) {
            currentUserPic = userpic1
        }
        //sets the user singleton object's info
        User.profilepic = currentUserPic!!.drawable
        sp?.edit()?.putInt("pic",resourceId)?.apply()

    }

    /**
     * This function changes the background of user pic when clicked & sets the previous one's background
     * back to white
     *
     * @param img the selected user picture
     */
    private fun handlesClick(img: ImageView?): Unit {
        if (currentUserPic != null) {
            currentUserPic!!.setBackgroundColor(Color.parseColor("#FFFFFF"))
            currentUserPic = img
            img!!.setBackgroundColor((Color.parseColor("#D1427A")))
        } else {
            currentUserPic = img
            img!!.setBackgroundColor((Color.parseColor("#D1427A")))
        }
    }

    // method for bitmap to base64
    private fun encodeTobase64(image: Bitmap): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b, Base64.DEFAULT)

        Log.d("Image Log:", imageEncoded)
        return imageEncoded
    }
}
