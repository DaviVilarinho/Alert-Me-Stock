package com.example.alertmestock.viewmodel
import Stock
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alertmestock.R

class StockAdapter(private val stockList:MutableList<Stock>): RecyclerView.Adapter<StockViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_view_stocks, parent, false)

        return StockViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val item = holder.itemView

        val name = item.findViewById<TextView>(R.id.stock_name)
        val price= item.findViewById<TextView>(R.id.stock_price)
        val reach= item.findViewById<TextView>(R.id.check)

        name.text = stockList[position].tick
        price.text = stockList[position].last_price.toString()
        reach.text = stockList[position].reached
    }

}

