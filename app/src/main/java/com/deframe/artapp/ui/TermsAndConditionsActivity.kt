package com.deframe.artapp.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.deframe.artapp.R


class TermsAndConditionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Terms of Use"
        setContentView(R.layout.terms_and_conditions)
    }

}

