package com.example.firstapp.reinaldo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstapp.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnSaludApp = findViewById<Button>(R.id.btnSaludApp)
        btnSaludApp.setOnClickListener { navigateToSaludApp() }

        val btnIMC = findViewById<Button>(R.id.btnIMC)
        btnIMC.setOnClickListener { navigateToIMCApp() }

        val btnTODO = findViewById<Button>(R.id.btnTODO)
        btnTODO.setOnClickListener { navigateToTODOApp() }
    }

    private fun navigateToTODOApp() {
        val intent = Intent(this, TODOActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMCApp() {
        val intent = Intent(this, IMCActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSaludApp(){
        val intent = Intent(this, firstAppActivity::class.java)
        startActivity(intent)
    }

}