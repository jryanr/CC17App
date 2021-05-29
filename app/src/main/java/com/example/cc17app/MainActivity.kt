package com.example.cc17app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cc17app.adapters.RestaurantAdapter
import com.example.cc17app.models.Restaurant
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_restaurant_menu.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    var restoList= ArrayList<Restaurant>()
    val displayList= ArrayList<Restaurant>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        restoList.add(Restaurant(name ="Chowking", location = "Baguio", R.mipmap.resto_chowking))
        restoList.add(Restaurant(name ="Greenwich", location = "Baguio", R.mipmap.resto_greenwich))
        restoList.add(Restaurant(name ="Good Taste", location = "Baguio", R.mipmap.resto_goodtaste))
        restoList.add(Restaurant(name ="Jollibee", location = "Baguio", R.mipmap.resto_jollibee))
        restoList.add(Restaurant(name ="KFC", location = "Baguio", R.mipmap.resto_kfc))
        restoList.add(Restaurant(name ="Mang Inasal", location = "Baguio", R.mipmap.resto_manginasal))
        restoList.add(Restaurant(name ="McDonald's", location = "Baguio", R.mipmap.resto_mcdonalds))
        restoList.add(Restaurant(name ="Pet's Bulaluhan", location = "Baguio", R.mipmap.resto_pets))
        restoList.add(Restaurant(name ="Pizzahut", location = "Baguio", R.mipmap.resto_pizzahut))
        restoList.add(Restaurant(name ="Yellow Cab", location = "Baguio", R.mipmap.resto_yellowcab))
        displayList.addAll(restoList)

        val rvAdapter = RestaurantAdapter(displayList, this)

        restaurantRecyclerView.layoutManager = LinearLayoutManager(this)
        restaurantRecyclerView.adapter = rvAdapter

        /*startActivity(Intent(this, RestaurantMenu::class.java))*/

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.resto_menu,menu)
        val menuItem = menu!!.findItem(R.id.search)

        if(menuItem!=null){
            val searchView = menuItem.actionView as SearchView
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        displayList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        restoList.forEach{
                            if(it.name.toLowerCase(Locale.getDefault()).contains(search)){
                                displayList.add(it)
                            }
                        }
                        restaurantRecyclerView.adapter!!.notifyDataSetChanged()
                    }
                    else {
                        displayList.clear()
                        displayList.addAll(restoList)
                        restaurantRecyclerView.adapter!!.notifyDataSetChanged()
                    }

                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}