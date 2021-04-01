package com.example.expandablerecyclerview.module.notice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.module.category.CategoryActivity
import kotlinx.android.synthetic.main.activity_notice.*

class NoticeActivity : AppCompatActivity() {
    lateinit var noticeViewModel: NoticeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        noticeViewModel=ViewModelProviders.of(this).get(NoticeViewModel::class.java)
        setUpEvents()
        observeValue()
    }

    private fun observeValue() {
        noticeViewModel.noticeArrayList.observe(this, Observer {notice->
           if (notice.isEmpty()){
               linearLayout.visibility=View.VISIBLE
           }else{
               linearLayout.visibility=View.GONE
           }
            rvNotice.adapter=NoticeAdapter(notice)
        })
        noticeViewModel.noticeProgressBar.observe(this, Observer {progressValue->
            if (progressValue){
                noticeProgressBar.visibility=View.VISIBLE
            }else{
                noticeProgressBar.visibility=View.GONE
            }

        })
    }

    private fun setUpEvents() {
            noticeViewModel.getNotice()
    }

    override fun onBackPressed() {

        startActivity(Intent(this,CategoryActivity::class.java))
        finish()
    }

}