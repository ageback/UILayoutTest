package com.example.uilayouttest.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uilayouttest.R
import kotlinx.android.synthetic.main.activity_file_persistence_test.*
import java.io.*
import java.lang.StringBuilder

class FilePersistenceTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_persistence_test)
        val inputText = loadFile()
        if(inputText.isNotEmpty())
        {
            txtToSave.setText(inputText)
            txtToSave.setSelection(inputText.length)
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadFile(): String {
        val content = StringBuilder()
        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e: IOException)
        {
            e.printStackTrace()
        }
        return content.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        save(txtToSave.text.toString())
    }

    private fun save(text: String)
    {
        try{
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(output))
            writer.use {
                it.write(text)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}