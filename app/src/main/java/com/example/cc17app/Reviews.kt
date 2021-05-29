package com.example.cc17app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class Reviews : AppCompatActivity() {
    var reviewsArray: ArrayList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reviews)

        val holder = arrayOf("Chicken skin was very crunchy yet the meat was very juicy. Will order again!",
                "Tasted okay, not that great.",
                "Food was good but rider was very rude!"

                )


        holder.forEach{
            reviewsArray.add(it)
        }

        adapter = ArrayAdapter<String>(this, R.layout.review, reviewsArray)
        val songsListView: ListView = findViewById<ListView>(R.id.reviewsLV)
        songsListView.adapter = adapter
    }
}