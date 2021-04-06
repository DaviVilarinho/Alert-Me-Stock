package com.example.alertmestock.view
import Stock
import android.view.LayoutInflater
import android.view.ViewGroup
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
        holder.name.text = stockList[position].tick
        holder.price.text = stockList[position].last_price.toString()
        holder.reach.text = stockList[position].reached
    }

}

