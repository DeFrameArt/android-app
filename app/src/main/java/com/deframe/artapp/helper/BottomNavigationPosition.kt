package com.deframe.artapp.helper

import android.support.v4.app.Fragment
import com.deframe.artapp.R
import com.deframe.artapp.ui.MapViewFragment
import com.deframe.artapp.ui.HomeFragment
import com.deframe.artapp.ui.ProfileFragment

enum class BottomNavigationPosition(val position: Int, val id: Int) {
    LIST(0, R.id.list),
    MAP(1, R.id.dashboard),
    //NOTIFICATIONS(2, R.id.notifications),
    PROFILE(2, R.id.profile);
}

fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.LIST.id -> BottomNavigationPosition.LIST
    BottomNavigationPosition.MAP.id -> BottomNavigationPosition.MAP
   // BottomNavigationPosition.NOTIFICATIONS.id -> BottomNavigationPosition.NOTIFICATIONS
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.LIST
}

fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.LIST -> HomeFragment.newInstance()
    BottomNavigationPosition.MAP -> MapViewFragment.newInstance()
    //BottomNavigationPosition.NOTIFICATIONS -> ListViewFragment.newInstance()
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.LIST -> HomeFragment.TAG
    BottomNavigationPosition.MAP -> MapViewFragment.TAG
   // BottomNavigationPosition.NOTIFICATIONS -> ListViewFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}

