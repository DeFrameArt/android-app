package com.deframe.artapp.helper

/**
 * This class handles the museum object
 *
 * @property name name of the museum
 * @property address address of the museum
 * @constructor constructor
 */
class Museum {
    private var name: String = ""
    private var address: String = ""

    /**
     * @param name
     * @param address
     * @return Museum object
     */
    constructor(name: String, address: String) {
        this.name = name
        this.address = address
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
        return this.name
    }


}
