package com.example.uilayouttest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.uilayouttest.Fruit
import com.example.uilayouttest.adapters.ListViewFruitAdapter
import com.example.uilayouttest.R
import kotlinx.android.synthetic.main.activity_list_view.*

class ListViewActivity : AppCompatActivity() {
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        initFruits()
        val adapter= ListViewFruitAdapter(
            this,
            R.layout.fruit_item,
            fruitList
        )
        listView.adapter=adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits(){
        repeat(5){
            fruitList.add(
                Fruit(
                    "Apple",
                    R.drawable.apple_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Banana",
                    R.drawable.banana_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Orange",
                    R.drawable.orange_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Watermelon",
                    R.drawable.watermelon_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Grape",
                    R.drawable.grape_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Pineapple",
                    R.drawable.pineapple_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Strawberry",
                    R.drawable.strawberry_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Cherry",
                    R.drawable.cherry_pic
                )
            )
            fruitList.add(
                Fruit(
                    "Mango",
                    R.drawable.mango_pic
                )
            )
        }
    }
}