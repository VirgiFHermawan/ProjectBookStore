package com.kotlin.projectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnmulai = findViewById<Button>(R.id.btnmulai)
        btnmulai.setOnClickListener {
            val intent = Intent(this, CrudActivity::class.java)
            startActivity(intent)
        }
    }
}