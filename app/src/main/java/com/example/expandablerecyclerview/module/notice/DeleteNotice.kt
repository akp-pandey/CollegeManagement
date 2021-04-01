package com.example.expandablerecyclerview.module.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_delete_notice.*

class DeleteNotice : AppCompatActivity() {
    lateinit var viewModel: NoticeViewModel
    var firestore:FirebaseFirestore= FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_notice)
        viewModel=ViewModelProviders.of(this).get(NoticeViewModel::class.java)
        viewModel.getNotice()
        val deletAdapter=MyDeleteAdapter{notice->
                firestore.collection("Notice").get().addOnSuccessListener {result->
                    result.forEach {noticeInfo->
                        if (noticeInfo.get("notice").toString().equals(notice.notice))
                        {
                            firestore.collection("Notice").document(noticeInfo.id).delete()
                            viewModel.getNotice()
                        }

                    }

                }

        }
        viewModel.noticeArrayList.observe(this, Observer {notice->
            if (notice.isEmpty()){
                noNoticeLayout.visibility=View.VISIBLE
            }else{
                noNoticeLayout.visibility=View.GONE
            }
                deletAdapter.submitList(notice)
                rvNoticeDelete.adapter=deletAdapter
                deletAdapter.notifyDataSetChanged()

        })
    }
}