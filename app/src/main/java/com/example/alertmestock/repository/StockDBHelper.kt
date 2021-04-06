package com.example.alertmestock.repository

import Stock
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class StockDBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"
        if (oldVersion != newVersion) db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "AlertMeStocks.db"
        const val TABLE_NAME = "stocks"
        const val ID = "id"
        const val TICK = "tick"
        const val PRICE = "price"
        const val TARGET_PRICE = "targetPrice"
        const val IS_ASC = "isAsc"
        const val SQL_CREATE_ENTRIES = "CREATE TABLE $TABLE_NAME (" +
                "$ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$TICK TEXT," +
                "$PRICE REAL," +
                "$TARGET_PRICE REAL," +
                "$IS_ASC INTEGER)"
    }

    val dbHelper = StockDBHelper(context)

    fun newStock(stock:Stock) {
        val db = writableDatabase ?: return
        val SQL_INSERT_ENTRIES = "INSERT INTO $TABLE_NAME ($TICK,$PRICE,$TARGET_PRICE,$IS_ASC) VALUES (" +
                "${stock.tick}," +
                "${stock.last_price}," +
                "${stock.target_price}," +
                "${boolToInt(stock.isAsc)})"
        db.execSQL(SQL_INSERT_ENTRIES)
        db.close()
    }

    fun delStock(id:Int) {
        val db = writableDatabase ?: return
        val SQL_DEL = "DELETE FROM $TABLE_NAME WHERE $ID = $id"
        db.execSQL(SQL_DEL)
        db.close()
    }

    fun updateStock(id:Int, stock: Stock) {
        val db = writableDatabase ?: return
        val SQL_UPDATE_ELEMENT = "UPDATE $stock SET $TICK = ${stock.tick}, $PRICE = ${stock.last_price}, $TARGET_PRICE = ${stock.target_price}, $IS_ASC = ${boolToInt(stock.isAsc)} WHERE $ID = $id"
        db.execSQL(SQL_UPDATE_ELEMENT)
        db.close()
    }

    fun getStocks(search: String?):MutableList<Stock> {
        val db = readableDatabase ?: return mutableListOf()
        val find = mutableListOf<Stock>()
        var cursor: Cursor
        var where:String
        if (search != null) where = "$TICK LIKE $search"
        else where = "$TICK IS NOT NULL"

        cursor = db.query(TABLE_NAME, null, where, null, null, null, null)

        if (cursor == null) {db.close(); return mutableListOf()}
        while(cursor.moveToNext()){
            var stock = Stock(cursor.getString(cursor.getColumnIndex(TICK)),
                            cursor.getString(cursor.getColumnIndex(PRICE)).toDouble(),
                            cursor.getString(cursor.getColumnIndex(TARGET_PRICE)).toDouble(),
                            intToBool(cursor.getString(cursor.getColumnIndex(IS_ASC)).toInt())
                )

            find.add(stock)
        }

        db.close()

        return find
    }

    fun boolToInt(b:Boolean):Int = if(b) 1 else 0
    fun intToBool(i:Int):Boolean = i == 1
}
