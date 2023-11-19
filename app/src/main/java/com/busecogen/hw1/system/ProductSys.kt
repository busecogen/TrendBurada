package com.busecogen.hw1.system

import com.busecogen.hw1.R
import com.busecogen.hw1.base.Product
import java.util.Collections

class ProductSys {
    companion object {
        var productList = ArrayList<Product>()
        fun prepare() {
            Collections.addAll<Product>(
                productList,
                Product("Cowboy Hat","It is a cowboy hat with a special accessor on top of it",
                    R.drawable.ic_covboyhat,457.60),
                Product("Red Bag","It is a unique handmade red bag", R.drawable.ic_redbag,350.50),
                Product("Dress","It is a colorful dress for ladies", R.drawable.ic_covboyhat,500.99),
                Product("Blue Jeans","It is a skinny blue jean", R.drawable.ic_bluejeans,390.30)
            )
        }
    }
}