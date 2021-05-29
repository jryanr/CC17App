package com.example.cc17app.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cc17app.R
import com.example.cc17app.RestaurantMenu
import com.example.cc17app.models.Restaurant
import kotlinx.android.synthetic.main.restaurant.view.*

class RestaurantAdapter(val arrayList: ArrayList<Restaurant>, val context: Context):
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bindItems(restaurant: Restaurant){
            itemView.nameTV.text= restaurant.name
            itemView.locationTV.text= restaurant.location
            itemView.imageIV.setImageResource(restaurant.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.restaurant, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bindItems(arrayList[position])
        holder.itemView.setOnClickListener{
            val restaurant = arrayList.get(position)
            val name: String = restaurant.name
            val location: String = restaurant.location
            val image: Int = restaurant.image

            val intent = Intent(context, RestaurantMenu::class.java)
            intent.putExtra("name",name)
            intent.putExtra("location",location)
            intent.putExtra("image",image)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

}