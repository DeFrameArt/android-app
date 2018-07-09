package com.deframe.artapp.helper

import org.json.JSONObject

/**
 * This class handles the museum object
 *
 * @property name name of the museum
 * @property address address of the museum
 * @constructor constructor
 */
class Museum {
    private var museumId: Int = 0
    private var name: String = ""
    private var address: String = ""
    private var url : String = ""
    private var json : JSONObject? = null

    /**
     * @param name
     * @param address
     * @param url
     * @param json
     * @return Museum object
     */
    constructor(museumId: Int, name: String, address: String, url:String, json:JSONObject) {
        this.museumId = museumId
        this.name = name
        this.address = address
        this.url = url
        this.json = json
    }

    /**
     *
     * @return String name of the museum
     */
    fun getName(): String {
        return this.name
    }

    /**
     *
     * @return String address of the museum
     */
    fun getAddress(): String {
        return this.address
    }

    /**
     *
     * @return museum's image banner url
     */
    fun getUrl(): String {
        return this.url
    }

    /**
     *
     * @return museum's image banner url
     */
    fun getId(): Int {
        return this.museumId
    }

    /**
     *
     * @return museum's image banner url
     */
    fun getJson(): JSONObject {
        return this.json!!
    }



}
