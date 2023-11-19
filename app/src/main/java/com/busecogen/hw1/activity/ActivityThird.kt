package com.busecogen.hw1.activity

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Spinner
import com.busecogen.hw1.R
import com.busecogen.hw1.base.ChartProduct
import com.busecogen.hw1.base.Product
import com.busecogen.hw1.databinding.ActivityThirdBinding

class ActivityThird : AppCompatActivity(), View.OnClickListener {
    lateinit var customDialog: Dialog
    lateinit var btnSave: Button
    lateinit var btnCancel: Button
    lateinit var seekBarTip:SeekBar
    lateinit var bindingThird: ActivityThirdBinding
    lateinit var receivedChartItem: ChartProduct
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingThird = ActivityThirdBinding.inflate(layoutInflater)

        getSupportActionBar()?.hide(); //hide title bar
        @Suppress("DEPRECATION")
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN) //enable full screen

        setContentView(bindingThird.root)
        receivedChartItem = intent.getParcelableExtra("chartproduct")!!
        val total = receivedChartItem.getNumber() * receivedChartItem.getPrice()
        val st= "$receivedChartItem\nTotal: $${"%.1f".format(total)}"
        bindingThird.tvItemName.text= st

        bindingThird.btnPurchase.setOnClickListener(this)
    }
    override fun onClick(view: View) {
        when(view.id){
            R.id.btnPurchase -> completePurchase()
        }
    }

    private fun completePurchase() {
        createDialog()
    }

    private fun createDialog() {
        customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog)

        btnSave = customDialog.findViewById(R.id.btnSave)
        btnCancel = customDialog.findViewById(R.id.btnCancel)
        seekBarTip = customDialog.findViewById(R.id.seekBarTip)

        btnSave.setOnClickListener {
            val seekBarValue = seekBarTip.progress
            customDialog.dismiss()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            intent.putExtra("msg", "Previous Experience: $seekBarValue")
            startActivity(intent)
            finish()

        }

        btnCancel.setOnClickListener {
            customDialog.dismiss()
        }

        customDialog.show()
    }





}