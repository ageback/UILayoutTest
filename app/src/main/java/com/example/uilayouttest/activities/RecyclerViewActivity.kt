package com.example.uilayouttest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.uilayouttest.Fruit
import com.example.uilayouttest.R
import com.example.uilayouttest.adapters.RecyclerViewFruitAdapter
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

    private  val fruitList=ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initFruits()
//        val layoutManager = LinearLayoutManager(this)
//        layoutManager.orientation=LinearLayoutManager.HORIZONTAL

        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager=layoutManager
        val adapter=
            RecyclerViewFruitAdapter(fruitList)
        recyclerView.adapter=adapter
    }

    private fun initFruits(){
        repeat(5){
            fruitList.add(
                Fruit(
                    getRandomLengthString("Apple"),
                    R.drawable.apple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Banana"),
                    R.drawable.banana_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Orange"),
                    R.drawable.orange_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Watermelon"),
                    R.drawable.watermelon_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Grape"),
                    R.drawable.grape_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Pineapple"),
                    R.drawable.pineapple_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Strawberry"),
                    R.drawable.strawberry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Cherry"),
                    R.drawable.cherry_pic
                )
            )
            fruitList.add(
                Fruit(
                    getRandomLengthString("Mango"),
                    R.drawable.mango_pic
                )
            )
        }
    }

    private fun getRandomLengthString(str: String): String{
        val n = (1..20).random()
        val builder=StringBuilder()
        repeat(n)
        {
            builder.append(str)
        }
        return builder.toString()
    }
}