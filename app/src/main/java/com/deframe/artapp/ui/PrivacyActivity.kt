package com.deframe.artapp.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.deframe.artapp.R

/**
 * This class handles the privacy screen
 */
class PrivacyActivity : AppCompatActivity() {

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
    }
}
