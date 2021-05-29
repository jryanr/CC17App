package com.example.cc17app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.*
import com.example.cc17app.adapters.MenuAdapter
import com.example.cc17app.models.Menu_Item

class RestaurantMenu : AppCompatActivity() {
    var cart_prices = ArrayList<Int>()
    var cart_items = ArrayList<String>()
    lateinit var adapter: MenuAdapter
    var itemList = mutableListOf<Menu_Item>()
    var combination = ArrayList<String>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_menu)

        var menuListview = findViewById<ListView>(R.id.menuListview)


        itemList.add(Menu_Item(name = "6 - pc. Chickenjoy - Solo","₱439", R.mipmap.jollibee_bucket))
        itemList.add(Menu_Item(name = "6 - pc. Chickenjoy - w/ Rice and Drinks","₱495", R.mipmap.jollibee_bucketvm))
        itemList.add(Menu_Item(name = "8 - pc. Chickenjoy - Solo","₱549", R.mipmap.jollibee_bucket))
        itemList.add(Menu_Item(name = "1 - pc. ChickenJoy with Jolly Spaghetti","₱109", R.mipmap.image2))
        itemList.add(Menu_Item(name = "1 - pc. ChickenJoy - VM","₱105", R.mipmap.image1))
        itemList.add(Menu_Item(name = "1 - pc. Jolly Spaghetti - VM","₱66", R.mipmap.jollibee_spagvm))
        itemList.add(Menu_Item(name = "1 - pc. Palabok - VM","₱105", R.mipmap.jollibee_palabok))
        itemList.add(Menu_Item(name = "1 - pc. Burgersteak - Solo","₱105", R.mipmap.jollibee_burgersteak))
        itemList.add(Menu_Item(name = "1 - pc. Yumburger - Solo","₱39", R.mipmap.jollibee_yumburger))
        itemList.add(Menu_Item(name = "1 - pc. Cheesy Yumburger - Solo","₱54", R.mipmap.jollibee_cheeseburger))
        itemList.add(Menu_Item(name = "1 - pc. Jolly Hotdog - Solo","₱55", R.mipmap.jollibee_jollyhotdog))

        findViewById<Button>(R.id.reviewsBTN).setOnClickListener{
            startActivity(Intent(applicationContext, Reviews::class.java))
        }

        findViewById<Button>(R.id.locationBTN).setOnClickListener{
            startActivity(Intent(applicationContext, RestoLocation::class.java))
        }




        menuListview.adapter = MenuAdapter(this, R.layout.menu_items, itemList)
        registerForContextMenu(menuListview)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater =menuInflater
        inflater.inflate(R.menu.menu_items_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.go_to_account_act -> {
                true
            }
            R.id.go_to_cart -> {
                var total: Int = cart_prices.sum()

                val intent = Intent(this, Cart::class.java)
                intent.putIntegerArrayListExtra("total_items_price", cart_prices)
                intent.putStringArrayListExtra("cart_items", cart_items)
                intent.putStringArrayListExtra("combination", combination)
                intent.putExtra("total", total)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_actions, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = info.position
        return when (item.itemId){
            R.id.add_to_cart -> {
                cart_items.add(itemList[position].name)
                val one = itemList[position].price
                val two = one.filter { it.isDigit() }
                /*var total: Int = cart_prices.sum()
                val subtotal: TextView = findViewById(R.id.subtotal) as TextView
                subtotal.text = "Total: ₱"+total.toString()*/
                content()
                cart_prices.add(two.toInt())
                /*val first = itemList[position].name+ itemList[position].price*/
                combination.add(itemList[position].name + " " +itemList[position].price)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    private fun content() {
        var default: String = "Total: ₱0"
        var holder = cart_prices.sum()
        default = "Total: ₱"+holder.toString()
        findViewById<TextView>(R.id.subtotal).text = default
        refresh(500)

    }
    private fun refresh(milliseconds: Long) {
        val handler = Handler()
        val runnable = Runnable { content() }
        handler.postDelayed(runnable, milliseconds)
    }

}