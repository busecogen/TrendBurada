package com.busecogen.hw1.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.busecogen.hw1.R
import com.busecogen.hw1.adapter.ColorSpinnerAdapter
import com.busecogen.hw1.base.ChartProduct
import com.busecogen.hw1.base.ProductColor
import com.busecogen.hw1.base.Product
import com.busecogen.hw1.databinding.ActivitySecondBinding
import java.util.Collections

class ActivitySecond : AppCompatActivity(), View.OnClickListener {
    lateinit var numberItems: ArrayList<String>
    lateinit var productColorItems: ArrayList<ProductColor>
    lateinit var colorAdapter:ColorSpinnerAdapter
    lateinit var numberAdapter: ArrayAdapter<String>
    lateinit var bindingSecond: ActivitySecondBinding
    lateinit var receivedProduct: Product
    var selectedNumber:Int=0
    lateinit var selectedColor:ProductColor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSecond = ActivitySecondBinding.inflate(layoutInflater)

        getSupportActionBar()?.hide(); //hide title bar
        @Suppress("DEPRECATION")
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) //enable full screen

        setContentView(bindingSecond.root)
        receivedProduct = intent.getParcelableExtra("product")!!

        updateUIWithProductData()

        prepareNumberSpinner()
        numberAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numberItems)
        bindingSecond.spinnerItemNumber.setAdapter(numberAdapter)
        bindingSecond.spinnerItemNumber.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
                selectedNumber = numberItems.get(position).toInt()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        prepareColorSpinner()
        colorAdapter = ColorSpinnerAdapter(this, productColorItems)
        bindingSecond.spinnerItemColor.adapter=colorAdapter
        bindingSecond.spinnerItemColor.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected( parent: AdapterView<*>?,view: View,position: Int,id: Long) {
                selectedColor = productColorItems.get(position)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        bindingSecond.btnGoChart.setOnClickListener(this)
        bindingSecond.btnReturnShopping.setOnClickListener(this)
    }

    private fun updateUIWithProductData() {
        bindingSecond.tvAboutName.text = receivedProduct.name
        bindingSecond.tvAboutDesc.text = receivedProduct.description
        bindingSecond.tvAboutPrice.text = "$" + receivedProduct.price.toString()
    }


    override fun onClick(view: View) {
        when(view.id){
            R.id.btnGoChart -> goToChart()
            R.id.btnReturnShopping -> goToMainActivity()
        }
    }

    private fun goToMainActivity(){
        val intentResult = Intent()
        intentResult.putExtra("msg", "Continue Shopping Please")
        setResult(RESULT_OK, intentResult)
        finish()
    }

    private fun goToChart(){
        var chartProduct:ChartProduct= ChartProduct(receivedProduct.name,selectedColor.getName().toString(),selectedNumber, receivedProduct.price)
        var secondActivityIntent: Intent = Intent(this@ActivitySecond, ActivityThird::class.java).apply {
            putExtra("chartproduct", chartProduct)
        }
        startActivity(secondActivityIntent)
    }

    private fun prepareNumberSpinner() {
        numberItems = ArrayList()
        Collections.addAll(numberItems, "1", "2", "3", "4", "5", "6", "7")
    }

    fun prepareColorSpinner() {
        productColorItems = ArrayList()
        Collections.addAll<ProductColor>(
            productColorItems,
            ProductColor("Blue", R.drawable.solid_blue),
            ProductColor("Pink", R.drawable.solid_pink),
            ProductColor("Green",R.drawable.solid_green),
            ProductColor("Purple",R.drawable.solid_purple),
            ProductColor("Orange",R.drawable.solid_orange),
            ProductColor("Red",R.drawable.solid_red),
        )
    }
}