package com.example.alertmestock.view

import Stock
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView
import com.example.alertmestock.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        mRecyclerView.adapter = StockAdapter(getAllStockData())

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
            // TODO: 05/04/21 method add stock to observing list

        }
    }
    fun getAllStockData():MutableList<Stock> {
        val mock = mutableListOf<Stock>(Stock("AAPL", 59.5, 60.0, true),
            Stock("BBAS", 30.5, 31.0, false),
            Stock("BBDC", 23.5, 22.0, true),
            Stock("B3SA", 60.0, 61.0, false)
        )
        return mock
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}