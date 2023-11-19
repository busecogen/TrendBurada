package com.busecogen.hw1.base

import android.os.Parcel
import android.os.Parcelable

class Product(): Parcelable{
    lateinit  var name: String
    lateinit var description: String
    var imgId = 0
    var price : Double = 0.0

    constructor(parcel: Parcel) : this() {
        name = parcel.readString().toString()
        description = parcel.readString().toString()
        imgId = parcel.readInt()
        price = parcel.readDouble()
    }

    constructor(name: String, description: String,imgId:Int, price: Double):this(){
        this.name = name
        this.description = description
        this.imgId = imgId
        this.price = price
    }

    override fun toString(): String {
        return """
            name='$name', description='$description', imgId=$imgId, price=$price
            
            """.trimIndent()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeInt(imgId)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Product> {
        override fun createFromParcel(parcel: Parcel): Product {
            return Product(parcel)
        }

        override fun newArray(size: Int): Array<Product?> {
            return arrayOfNulls(size)
        }
    }
}