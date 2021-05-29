package com.example.cc17app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.cc17app.R
import com.example.cc17app.models.Menu_Item

class MenuAdapter (var mCtx:Context, var resources: Int, var items:List<Menu_Item>):ArrayAdapter<Menu_Item>(mCtx,resources,items){
    override fun getView(position:Int, convertView: View?, parent: ViewGroup):View{
        val layoutInflater:LayoutInflater = LayoutInflater.from(mCtx)
        val view:View= layoutInflater.inflate(resources, null)

        val imageView: ImageView = view.findViewById(R.id.image)
        val nameTV: TextView = view.findViewById(R.id.nameTV)
        val priceTV: TextView = view.findViewById(R.id.priceTV)

        var mItem:Menu_Item = items[position]
        imageView.setImageDrawable(mCtx.resources.getDrawable(mItem.image))
        nameTV.text = mItem.name
        priceTV.text= mItem.price

        return view

    }
}