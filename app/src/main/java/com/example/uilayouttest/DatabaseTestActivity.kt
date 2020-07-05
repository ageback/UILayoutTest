package com.example.uilayouttest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.uilayouttest.database.MyDatabaseHelper
import kotlinx.android.synthetic.main.activity_database_test.*

class DatabaseTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_test)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        btnCreateDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        btnAddDbData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages",510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)
        }

        btnUpdateDbData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
        }

        btnQueryDbData.setOnClickListener {
            val db = dbHelper.writableDatabase
            var cursor = db.query("Book", null, null, null, null, null, null)
            if(cursor.moveToFirst())
            {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d("DatabaseTestActivity", "book name is $name")
                    Log.d("DatabaseTestActivity", "book author is $author")
                    Log.d("DatabaseTestActivity", "book pages is $pages")
                    Log.d("DatabaseTestActivity", "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
    }
}