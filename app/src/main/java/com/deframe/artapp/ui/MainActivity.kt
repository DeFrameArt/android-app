package com.deframe.artapp.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.deframe.artapp.extension.active
import com.deframe.artapp.extension.disableShiftMode
import com.deframe.artapp.helper.BottomNavigationPosition
import com.deframe.artapp.helper.createFragment
import com.deframe.artapp.helper.findNavigationPositionById
import com.deframe.artapp.helper.getTag
import com.deframe.artapp.R
import com.google.android.gms.maps.SupportMapFragment

/**
 * This class handles the main fragment
 *
 * @property navPosition
 * @property toolBar
 * @property bottomNavigation
 */
class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private val KEY_POSITION = "keyPosition"

    private var navPosition: BottomNavigationPosition = BottomNavigationPosition.LIST

    private lateinit var toolbar: Toolbar

    private lateinit var bottomNavigation: BottomNavigationView

    /**
     * Handles onCreate actions of the activity
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreSaveInstanceState(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        bottomNavigation = findViewById(R.id.bottom_navigation)
        setSupportActionBar(toolbar)
        initBottomNavigation()
        initFragment(savedInstanceState)
    }

    /**
     * Handles onSavedInstantceState actions of the activity
     *
     * @param outState
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        // Store the current navigation position.
        outState?.putInt(KEY_POSITION, navPosition.id)
        super.onSaveInstanceState(outState)
    }

    /**
     * Handles onSavedInstantceState actions of the activity
     *
     * @param outState
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navPosition = findNavigationPositionById(item.itemId)
        return switchFragment(navPosition)
    }

    /**
     * Restore the current navigation position.
     * @param savedInstanceState
     */
    private fun restoreSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.also {
            val id = it.getInt(KEY_POSITION, BottomNavigationPosition.LIST.id)
            navPosition = findNavigationPositionById(id)
        }
    }

    /**
     * Initiates bottome navigation bar
     */
    private fun initBottomNavigation() {
        bottomNavigation.disableShiftMode() // Extension function
        bottomNavigation.active(navPosition.position)   // Extension function
        bottomNavigation.setOnNavigationItemSelectedListener(this)
    }

    /**
     * Initializes fragment
     * @param savedInstanceState
     */
    private fun initFragment(savedInstanceState: Bundle?) {
        savedInstanceState ?: switchFragment(BottomNavigationPosition.LIST)
    }

    /**
     * Immediately execute transactions with FragmentManager#executePendingTransactions.
     */
    private fun switchFragment(navPosition: BottomNavigationPosition): Boolean {
        val fragment = supportFragmentManager.findFragment(navPosition)
        if (fragment.isAdded) return false
        detachFragment()
        attachFragment(fragment, navPosition.getTag())
        supportFragmentManager.executePendingTransactions()
        return true
    }

    /**
     * @return found fragment according to tag
     */
    private fun FragmentManager.findFragment(position: BottomNavigationPosition): Fragment {
        return findFragmentByTag(position.getTag()) ?: position.createFragment()
    }

    /**
     * Detaches fragment
     */
    private fun detachFragment() {
        supportFragmentManager.findFragmentById(R.id.container)?.also {
            supportFragmentManager.beginTransaction().detach(it).commit()
        }
    }

    /**
     * Attaches fragment
     * @param fragment
     * @param tag
     */
    private fun attachFragment(fragment: Fragment, tag: String) {
        if (fragment.isDetached) {
            supportFragmentManager.beginTransaction().attach(fragment).commit()
        } else {
            supportFragmentManager.beginTransaction().add(R.id.container, fragment, tag).commit()
        }
        // Set a transition animation for this transaction.
        supportFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
    }


}
