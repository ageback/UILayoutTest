package com.example.uilayouttest.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.example.uilayouttest.MainActivity
import com.example.uilayouttest.R
import com.example.uilayouttest.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : BaseActivity() {
    private val TAG_REMEMBER_PASS =  "remember_password"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }

        val prefs = getPreferences(Context.MODE_PRIVATE)
        val isRemember = prefs.getBoolean(TAG_REMEMBER_PASS, false)
        if(isRemember)
        {
            val account = prefs.getString("account", "")
            val password = prefs.getString("password", "")
            accountEdit.setText(account)
            passwordEdit.setText(password)
            chkRemberPass.isChecked = true
        }

        btnLogin.setOnClickListener {
            val account = accountEdit.text.toString()
            val password = passwordEdit.text.toString()

            if(account=="lmh" && password == "1234")
            {
                val editor = prefs.edit()
                if(chkRemberPass.isChecked)
                {
                    editor.putBoolean(TAG_REMEMBER_PASS, true)
                    editor.putString("account", account)
                    editor.putString("password", password)
                } else {
                    editor.clear()
                }
                editor.apply()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "account or password is invalid", Toast.LENGTH_SHORT).show()
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
}