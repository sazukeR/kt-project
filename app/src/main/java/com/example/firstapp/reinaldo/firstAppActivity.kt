package com.example.firstapp.reinaldo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import com.example.firstapp.R

class firstAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_app)
        val buttonStar = findViewById<AppCompatButton>(R.id.buttonStart)
        var etName = findViewById<AppCompatEditText>(R.id.etName)
        buttonStar.setOnClickListener {
            val name = etName.text.toString()
            if(name.isNotEmpty()) {
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("EXTRA_NAME", name)
                startActivity(intent)
            }
        }
    }
}