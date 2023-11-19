package com.busecogen.hw1.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.busecogen.hw1.R
import com.busecogen.hw1.base.ProductColor

class ColorSpinnerAdapter(var contextt: Context, var spinnerItemValues: ArrayList<ProductColor>)
    : ArrayAdapter<ProductColor>(contextt, R.layout.colorspinner_item, spinnerItemValues)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getColorView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getColorView(position, convertView, parent)
    }

    @SuppressLint("MissingInflatedId")
    fun getColorView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflator.inflate(R.layout.colorspinner_item, parent, false)

        val imgItemColor: ImageView = view.findViewById(R.id.imgColor)
        val tvItemColorName: TextView = view.findViewById(R.id.tvColor)

        val selectedColor = spinnerItemValues.get(position)
        tvItemColorName.text = selectedColor.getName()
        imgItemColor.setImageResource(selectedColor.getImgId())

        return view
    }
}