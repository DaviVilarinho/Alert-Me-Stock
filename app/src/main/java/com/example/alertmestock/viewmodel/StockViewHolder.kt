package com.example.alertmestock.viewmodel

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.alertmestock.R

class StockViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val name : TextView
    val price: TextView
    val reach: TextView
    init {
        name = itemView.findViewById<TextView>(R.id.stock_name)
        price = itemView.findViewById<TextView>(R.id.stock_price)
        reach = itemView.findViewById<TextView>(R.id.check)
    }
}
