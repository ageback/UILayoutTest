package com.example.uilayouttest.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uilayouttest.Fruit
import com.example.uilayouttest.R

class MDFruitAdapter(val context: Context, val fruitList:List<Fruit>) : RecyclerView.Adapter<MDFruitAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.mdFruitImage)
        val fruitName: TextView = view.findViewById(R.id.mdFruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MDFruitAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.md_fruit_item, parent, false)
        return ViewHolder(view)
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