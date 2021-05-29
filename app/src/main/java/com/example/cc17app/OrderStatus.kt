package com.example.cc17app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OrderStatus : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_status)



        findViewById<Button>(R.id.track_orderBTN).setOnClickListener{
            startActivity(Intent(applicationContext, Test::class.java))
        }
    }
}