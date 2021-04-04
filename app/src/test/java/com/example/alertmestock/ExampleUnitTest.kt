package com.example.alertmestock

import User
import com.example.alertmestock.service.FinProp
import com.example.alertmestock.service.FinPropService
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun testingApi() {
        val davi = User("", true)
        val fin = FinPropService(davi)

        val aapl: Array<FinProp> = fin.getStockData("AAPL")

        println(aapl[0].price)
    }
}