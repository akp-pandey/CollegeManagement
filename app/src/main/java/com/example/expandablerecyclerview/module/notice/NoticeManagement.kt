package com.example.expandablerecyclerview.module.notice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expandablerecyclerview.R
import kotlinx.android.synthetic.main.activity_add_notice.*

class NoticeManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notice)
        setUpEvents()
    }

    private fun setUpEvents() {
        addNotice.setOnClickListener {
            finish()
            startActivity(Intent(this,CreateNotice::class.java))

        }
        deleteNotice.setOnClickListener {
            finish()
            startActivity(Intent(this,DeleteNotice::class.java))

        }
    }
}