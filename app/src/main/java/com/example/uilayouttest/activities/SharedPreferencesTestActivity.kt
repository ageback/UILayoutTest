package com.example.uilayouttest.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.uilayouttest.R
import kotlinx.android.synthetic.main.activity_shared_preferences_test.*

class SharedPreferencesTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preferences_test)
        btnSavePreferences.setOnClickListener {
            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            editor.putString("name", "Tome")
            editor.putInt("age", 28)
            editor.putBoolean("married", false)
            editor.apply()
        }

        btnRestore.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d("SharedPreferences", "name is $name")
            Log.d("SharedPreferences", "age is $age")
            Log.d("SharedPreferences", "married is $married")
        }
    }
}