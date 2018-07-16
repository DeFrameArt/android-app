package com.deframe.artapp.helper

import org.json.JSONObject

/**
 * This singleton class handles the current user
 *
 * @property name name of the museum
 * @property address address of the museum
 * @constructor constructor
 */
class User {


    private var firstname: String = ""
    private var email: String = ""
    private var profilepic : String = ""

    /**
     * @param firstname
     * @param email
     * @param profilepic
     * @return Museum object
     */
    constructor(museumId: Int, name: String, address: String, url:String, json: JSONObject) {

    }

    /**
     *
     * @return String name of the museum
     */
    fun getName(): String {
        return this.firstname
    }

    /**
     *
     * @return String address of the museum
     */
    fun getEmail(): String {
        return this.email
    }

    /**
     *
     * @return museum's image banner url
     */
    fun getProfilePic(): String {
        return this.profilepic
    }
}
