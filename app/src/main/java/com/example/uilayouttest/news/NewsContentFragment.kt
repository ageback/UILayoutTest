package com.example.uilayouttest.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.uilayouttest.R
import kotlinx.android.synthetic.main.news_content_frag.*

class NewsContentFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.news_content_frag, container, false)
    }

    fun refresh(title: String, content: String)
    {
        newsContentLayout.visibility = View.VISIBLE
        newsTitle.text = title
        newsContent.text = content
    }
}