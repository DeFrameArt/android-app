package com.deframe.artapp.helper

import org.json.JSONObject

/**
 * This class handles the museum object
 *
 * @property name name of the museum
 * @property address address of the museum
 * @constructor constructor
 */
class Floorplan {
    private var id: Int = 0
    private var level: String = ""
    private var url : String = ""


    /**
     * @param name
     * @param address
     * @param url
     * @param json
     * @return Museum object
     */
    constructor(id: Int, level: String,url:String) {
        this.id = id
        this.level = level
        this.url = url
    }

    /**
     *
     * @return String level of the floorplan
     */
    fun getLevel(): String {
        return this.level
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
     * @return floorplan's id
     */
    fun getId(): Int {
        return this.id
    }
}
