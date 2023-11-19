package com.busecogen.hw1.activity

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.busecogen.hw1.system.ProductSys
import com.busecogen.hw1.R
import com.busecogen.hw1.base.ChartProduct
import com.busecogen.hw1.base.Product
import com.busecogen.hw1.databinding.ActivityMainBinding

//Added View.OnClickListener
class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: ActivityMainBinding
    private lateinit var images: Array<ImageView>
    private var imgIndex: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        getSupportActionBar()?.hide(); //hide title bar
        @Suppress("DEPRECATION")
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        ) //enable full screen

        setContentView(binding.root)
        startBlinkingAnimation(binding.tvLogo)

        ProductSys.prepare()

        images = arrayOf(
            binding.imgCovboyHat,
            binding.imgRedBag,
            binding.imgDress,
            binding.imgBlueJeans
        )

        binding.btnPrev.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)
        binding.btnAddChart.setOnClickListener(this)
        //Log.d("")
    }

    private fun getPrevImgIndex(): Int {
        return (imgIndex - 1 + images.size) % images.size
    }

    private fun getNextImgIndex(): Int {
        return (imgIndex + 1) % images.size
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnPrev -> imgIndex = getPrevImgIndex()
            R.id.btnNext -> imgIndex = getNextImgIndex()
            R.id.btnAddChart -> goToSecondActivity()
        }

        images[getPrevImgIndex()].visibility = View.INVISIBLE
        images[imgIndex].visibility = View.VISIBLE
        images[getNextImgIndex()].visibility = View.INVISIBLE
    }

    private fun goToSecondActivity() {
        val currentProduct = getCurrentProduct()
        //var c = Bundle()
        //c.putInt("keu",key)
        //intent.putExtra(c)
        var switchActivityIntent: Intent =
            Intent(this@MainActivity, ActivitySecond::class.java).apply {
                putExtra("product", currentProduct)
            }
        switchActivityLauncher.launch(switchActivityIntent)
    }

    private fun getCurrentProduct(): Product {
        // Assuming the order of images corresponds to the order of products
        return ProductSys.productList[imgIndex]
    }

    fun makeAndShowDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("TrendBurada")
        builder.setMessage(message)
        builder.setPositiveButton("Close") { dialog, which ->
        }
        builder.create()
        builder.show()
    }

    var switchActivityLauncher = registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val msg = data?.getStringExtra("msg")
            if (msg != null) {
                makeAndShowDialog(msg)
            }

        } else Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        // Handle the intent that has the 'msg' extra
        val msg = intent.getStringExtra("msg")
        if (msg != null) {
            makeAndShowDialog(msg)
        }
    }

    private fun startBlinkingAnimation(textView: TextView) {
        val colorStart = textView.currentTextColor
        val colorEnd = ContextCompat.getColor(this, android.R.color.transparent)

        val animator = ObjectAnimator.ofInt(
            textView, "textColor", colorStart, colorEnd
        )
        animator.duration = 1500 // duration of one blink
        animator.setEvaluator(ArgbEvaluator())
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }


}



