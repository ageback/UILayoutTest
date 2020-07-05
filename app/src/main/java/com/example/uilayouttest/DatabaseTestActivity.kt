package com.example.uilayouttest

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}