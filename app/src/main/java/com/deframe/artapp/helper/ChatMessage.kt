package com.deframe.artapp.helper

import org.json.JSONObject

/**
 * This class handles the museum object
 *
 * @property name name of the museum
 * @property address address of the museum
 * @constructor constructor
 */
class ChatMessage {
    private var username: String = ""
    private var rightText: String = ""
    private var leftText: String = ""
    private var json : JSONObject? = null

    constructor(username: String, rightText: String, leftText: String, json: JSONObject?) {
        this.username = username
        this.rightText = rightText
        this.leftText = leftText
        this.json = json
    }


    /**
     *
     * @return String name of the museum
     */
    fun getUsername(): String {
        return this.username
    }

    /**
     *
     * @return String name of the museum
     */
    fun getRightText(): String {
        return this.rightText
    }

    /**
     *
     * @return String name of the museum
     */
    fun getLeftText(): String {
        return this.leftText
    }

    fun setLeftText(str:String){
         this.leftText = str
    }

    /**
     *
     * @return museum's image banner url
     */
    fun getJson(): JSONObject {
        return this.json!!
    }



}