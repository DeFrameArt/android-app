package com.deframe.artapp.helper

import android.graphics.drawable.Drawable
import org.json.JSONObject

/**
 * This singleton class handles the current user's info
 *
 */
object User {
    var firstname = ""
    var lastname = ""
    var email = ""
    var profilepic: Drawable? = null
}
