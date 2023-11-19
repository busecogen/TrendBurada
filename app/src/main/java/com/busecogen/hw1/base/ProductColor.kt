package com.busecogen.hw1.base

class ProductColor {
    private var imgId = 0
    private var name: String? = null

    constructor(name: String?, imgId: Int) {
        this.name = name
        this.imgId = imgId
    }

    fun getName(): String? {
        return name
    }

    fun getImgId(): Int {
        return imgId
    }

}