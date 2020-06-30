package com.example.uilayouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.uilayouttest.database.MyDatabaseHelper
import kotlinx.android.synthetic.main.activity_database_test.*

class DatabaseTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_test)
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
        btnCreateDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }
    }
}