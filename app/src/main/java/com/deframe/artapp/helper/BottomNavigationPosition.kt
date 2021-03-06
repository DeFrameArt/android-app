package com.deframe.artapp.helper

import android.support.v4.app.Fragment
import com.deframe.artapp.R
import com.deframe.artapp.ui.*

/**
 *Enumerates the bottom navigation buttons
 * @property LIST
 * @property MAP
 * @property PROFILE
 */
enum class BottomNavigationPosition(val position: Int, val id: Int) {
    LIST(0, R.id.list),
    MAP(1, R.id.dashboard),
    BOT(2, R.id.Bot),
    SHOP(3, R.id.shopping),
    PROFILE(4, R.id.profile);
}

/**
 * Assigns id to navigation position
 *
 * @param id of the button
 * @return bottom navigation position according to the id
 */
fun findNavigationPositionById(id: Int): BottomNavigationPosition = when (id) {
    BottomNavigationPosition.LIST.id -> BottomNavigationPosition.LIST
    BottomNavigationPosition.MAP.id -> BottomNavigationPosition.MAP
    BottomNavigationPosition.BOT.id -> BottomNavigationPosition.BOT
    BottomNavigationPosition.SHOP.id -> BottomNavigationPosition.SHOP
    BottomNavigationPosition.PROFILE.id -> BottomNavigationPosition.PROFILE
    else -> BottomNavigationPosition.LIST
}

/**
 * Creates fragment according to selected botton
 *
 * @return fragment of corresponding button
 */
fun BottomNavigationPosition.createFragment(): Fragment = when (this) {
    BottomNavigationPosition.LIST -> ListViewFragment.newInstance()
    BottomNavigationPosition.MAP -> MapViewFragment.newInstance()
    BottomNavigationPosition.BOT -> BotFragment.newInstance()
    BottomNavigationPosition.SHOP -> ShopFragment.newInstance()
    BottomNavigationPosition.PROFILE -> ProfileFragment.newInstance()
}

/**
 * get tag of the button
 *
 * @return string tag of the button
 */
fun BottomNavigationPosition.getTag(): String = when (this) {
    BottomNavigationPosition.LIST -> ListViewFragment.TAG
    BottomNavigationPosition.MAP -> MapViewFragment.TAG
    BottomNavigationPosition.BOT -> BotFragment.TAG
    BottomNavigationPosition.SHOP -> ShopFragment.TAG
    BottomNavigationPosition.PROFILE -> ProfileFragment.TAG
}
