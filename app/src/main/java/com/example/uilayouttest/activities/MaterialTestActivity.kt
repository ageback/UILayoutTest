package com.example.uilayouttest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.uilayouttest.Fruit
import com.example.uilayouttest.R
import com.example.uilayouttest.adapters.MDFruitAdapter
import com.example.uilayouttest.utils.showSnackbar
import com.example.uilayouttest.utils.showToast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.drawerLayout
import kotlinx.android.synthetic.main.activity_login.fab
import kotlinx.android.synthetic.main.activity_login.navView
import kotlinx.android.synthetic.main.activity_login.toolbar
import kotlinx.android.synthetic.main.activity_material_test.*
import kotlin.concurrent.thread

class MaterialTestActivity : AppCompatActivity() {

    val fruits = mutableListOf(Fruit("Apple", R.drawable.apple),
    Fruit("Banana", R.drawable.banana),
    Fruit("Orange", R.drawable.orange),
    Fruit("Watermelon", R.drawable.watermelon),
    Fruit("Pear", R.drawable.pear),
    Fruit("Grape", R.drawable.grape),
    Fruit("Pineapple", R.drawable.pineapple),
    Fruit("Strawberry", R.drawable.strawberry),
    Fruit("Cherry", R.drawable.cherry),
    Fruit("Mango", R.drawable.mango)
    )

    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material_test)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        fab.setOnClickListener {
//            view -> Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
//                .setAction("Undo") {
//                Toast.makeText(this, "Data Restored", Toast.LENGTH_SHORT).show()
//            }.show()
          view ->
            run {
                view.showSnackbar(
                    "数据已删除",
                    "撤销",
                    Snackbar.LENGTH_SHORT,
                    { "数据已恢复".showToast(this) })
            }

        }

        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView2.layoutManager = layoutManager
        val adapter = MDFruitAdapter(this, fruitList)
        recyclerView2.adapter = adapter
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary)
        swipeRefreshLayout.setOnRefreshListener {
            refreshFruits(adapter)
        }
    }

    private fun refreshFruits(adapter:MDFruitAdapter)
    {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
            R.id.mnuBackup -> Toast.makeText(this, "你点击了备份", Toast.LENGTH_SHORT).show()
            R.id.mnuDelete -> Toast.makeText(this, "你点击了删除", Toast.LENGTH_SHORT).show()
            R.id.mnuSettings -> Toast.makeText(this, "你点击了设置", Toast.LENGTH_SHORT).show()
        }
        return true
    }

    private fun initFruits()
    {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }
}