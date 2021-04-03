package com.example.alertmestock.service

import User
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

// https://financialmodelingprep.com/api/v3/quote-short/AAPL?apikey=API_KEY
class FinPropService (val user: User) {
    private val BASE_URL = "https://financialmodelingprep.com/"


    fun getStockData(tick:String): String? {
        val HEADERS = "api/v3/quote-short/$tick/${user.KEY}"
        val client   = OkHttpClient()
        val request  = Request.Builder().url("$BASE_URL$HEADERS").build()
        val response = client.newCall(request).execute()

        return response.body()?.string()
    }
}