package com.example.uilayouttest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.uilayouttest.fragmenttest.AnotherRightFragment
import com.example.uilayouttest.fragmenttest.RightFragment
import kotlinx.android.synthetic.main.left_fragment.*

class FragmentTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_test)

        btnLeftFrag.setOnClickListener {
            replaceFragment(AnotherRightFragment())
        }
        replaceFragment(RightFragment())
    }

    private fun replaceFragment(fragment: Fragment)
    {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragRightLayout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}