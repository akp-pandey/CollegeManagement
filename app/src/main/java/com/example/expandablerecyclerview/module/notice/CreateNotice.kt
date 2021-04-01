package com.example.expandablerecyclerview.module.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import kotlinx.android.synthetic.main.activity_create_notice.*

class CreateNotice : AppCompatActivity() {
    lateinit var viewModel: NoticeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notice)
        viewModel=ViewModelProviders.of(this).get(NoticeViewModel::class.java)
        setUpEvents()
        observerValues()
    }

    private fun setUpEvents() {
        submitNotice.setOnClickListener {
            val date=noticeDate.dayOfMonth.toString()+"-"+noticeDate.month.toString()+"-"+noticeDate.year.toString()
            viewModel.postNotice(date,noticeDate.dayOfMonth.toString(),noticeDescription.text.toString(),this)
        }
    }

    private fun observerValues() {
        viewModel.progressBarValue.observe(this, Observer {value->
            if (value){
                npbar.visibility=View.VISIBLE
            }else{
                npbar.visibility=View.GONE
            }

        })
    }
}