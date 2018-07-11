package com.deframe.artapp.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.deframe.artapp.R

/**
 * This class handles the feedback screen
 */
class FeedbackActivity : AppCompatActivity() {

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback)
    }
}
