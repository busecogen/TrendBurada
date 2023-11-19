package com.busecogen.hw1.base

import android.os.Parcel
import android.os.Parcelable

class ChartProduct() :Parcelable {
    private lateinit var productName: String
    private lateinit var productColor: String
    private var number:Int = 0
    private var price:Double = 0.0

    constructor(parcel: Parcel) : this() {
        productName = parcel.readString().toString()
        productColor = parcel.readString().toString()
        number = parcel.readInt()
        price = parcel.readDouble()
    }

    constructor(productName: String, productColor: String, number:Int, price:Double) : this() {
        this.productName = productName
        this.productColor = productColor
        this.number=number
        this.price=price
    }

    override fun toString(): String {
        return "$number x \$$price - $productName ($productColor)\n"
    }

    fun getProductName(): String {
        return productName
    }

    fun getColor():String {
        return productColor
    }

    fun getNumber(): Int {
        return number
    }

    fun getPrice():Double {
        return price
    }


    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(productName)
        dest.writeString(productColor)
        dest.writeInt(number)
        dest.writeDouble(price)
    }
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ChartProduct> {
        override fun createFromParcel(parcel: Parcel): ChartProduct {
            return ChartProduct(parcel)
        }

        override fun newArray(size: Int): Array<ChartProduct?> {
            return arrayOfNulls(size)
        }
    }

}