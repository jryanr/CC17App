package com.example.cc17app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_cart.*

class Cart : AppCompatActivity() {
    var itemsArray: ArrayList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>
    var total_price_holder = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        val intent = intent
        var cart_items = intent.getStringArrayListExtra("cart_items")
        var combination = intent.getStringArrayListExtra("combination")
        var cart_prices = intent.getIntegerArrayListExtra("cart_prices")




        var total_price = intent.getIntExtra("total", 0)

        total_price = total_price+60
        total_price_holder = total_price


        var total_price_text = "Total: ₱"
        total_price_text = "Total: ₱"+total_price.toString()



        findViewById<TextView>(R.id.total_tv).text= total_price_text

        for (string in combination!!){
            itemsArray.add(string)
        }



        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itemsArray)
        val cart_listview: ListView = findViewById<ListView>(R.id.cart_list)
        cart_listview.adapter = adapter
        registerForContextMenu(cart_listview)

        findViewById<Button>(R.id.place_order_btn).setOnClickListener{
            startActivity(Intent(applicationContext, OrderStatus::class.java))
        }





    }

}





