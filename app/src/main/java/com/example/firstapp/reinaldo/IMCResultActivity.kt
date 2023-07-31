package com.example.firstapp.reinaldo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.accessibility.AccessibilityManager.AudioDescriptionRequestedChangeListener
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.firstapp.R
import com.example.firstapp.reinaldo.IMCActivity.Companion.IMC_KEY

private lateinit var tvTitleResult:TextView
private lateinit var tvIMCResult:TextView
private lateinit var tvIMCDescription:TextView
private lateinit var btnReCalculate:Button


class IMCResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcresult)
        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initListeners()
        initUI(result)
    }

    private fun initListeners() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

   private fun initUI(result: Double) {
        tvIMCResult.text = result.toString()
        when(result) {

           in 0.00..18.50 -> {
               tvTitleResult.text = getString(R.string.title_peso_bajo)
               tvTitleResult.setTextColor(ContextCompat.getColor(this, R.color.color_bajo_peso))
               tvIMCDescription.text = getString(R.string.desc_peso_bajo)
           }
           in 18.51..24.99 -> {
               tvTitleResult.text = getString(R.string.title_peso_optimo)
               tvTitleResult.setTextColor(ContextCompat.getColor(this, R.color.color_peso_optimo))
               tvIMCDescription.text = getString(R.string.desc_peso_optimo)

           }
           in 25.00..29.99 -> {
               tvTitleResult.text = getString(R.string.title_sobrepeso)
               tvTitleResult.setTextColor(ContextCompat.getColor(this, R.color.color_sobrepeso))
               tvIMCDescription.text = getString(R.string.desc_sobrepeso)
           }
           in 30.00..99.00 -> {
               tvTitleResult.text = getString(R.string.title_obesidad)
               tvTitleResult.setTextColor(ContextCompat.getColor(this, R.color.color_danger))
               tvIMCDescription.text = getString(R.string.desc_obesidad)
           }
           else -> {
               tvTitleResult.text = getString(R.string.error)
               tvIMCResult.text = getString(R.string.error)
               tvIMCDescription.text = getString(R.string.error)
           }

       }
   }


    private fun initComponents() {
        tvTitleResult = findViewById(R.id.tvTitleResult)
        tvIMCResult = findViewById(R.id.tvIMCResult)
        tvIMCDescription = findViewById(R.id.tvIMCDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}