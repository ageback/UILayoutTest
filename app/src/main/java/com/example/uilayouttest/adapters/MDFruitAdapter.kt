package com.example.uilayouttest.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uilayouttest.Fruit
import com.example.uilayouttest.R
import com.example.uilayouttest.activities.FruitActivity

class MDFruitAdapter(val context: Context, val fruitList:List<Fruit>) : RecyclerView.Adapter<MDFruitAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.mdFruitImage)
        val fruitName: TextView = view.findViewById(R.id.mdFruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MDFruitAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.md_fruit_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(context, FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
            }
            context.startActivity(intent)

        }
        return holder
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: MDFruitAdapter.ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)
    }
}