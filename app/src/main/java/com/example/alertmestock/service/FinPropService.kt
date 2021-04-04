package com.example.alertmestock.service

import User
import com.google.gson.*
import okhttp3.OkHttpClient
import okhttp3.Request

// https://financialmodelingprep.com/api/v3/quote-short/AAPL?apikey=API_KEY
class FinPropService (val user: User) {
    private val BASE_URL = "https://financialmodelingprep.com/"


    fun getStockData(tick:String): Array<FinProp> {
        val HEADERS = "api/v3/quote-short/$tick?apikey=${user.KEY}"
        val client   = OkHttpClient()
        val request  = Request.Builder().url("$BASE_URL$HEADERS").build()
        val response = client.newCall(request).execute()
//        if (!response.isSuccessful) return

         val meuobj = Gson().fromJson(response.body()!!.string(), Array<FinProp>::class.java)

        return meuobj
    }
}

