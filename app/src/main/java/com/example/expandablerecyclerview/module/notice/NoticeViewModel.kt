package com.example.expandablerecyclerview.module.notice

import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.expandablerecyclerview.model.Notice
import com.google.firebase.firestore.FirebaseFirestore

class NoticeViewModel(application: Application):AndroidViewModel(application) {
    val firestore:FirebaseFirestore= FirebaseFirestore.getInstance()
    val noticeArrayList by lazy { MutableLiveData<ArrayList<Notice>>() }
    val progressBarValue by lazy { MutableLiveData<Boolean>() }
    val noticeProgressBar by lazy { MutableLiveData<Boolean>() }

    fun getNotice(){
        noticeProgressBar.postValue(true)
        firestore.collection("Notice").get().addOnSuccessListener {querySnapshot ->
            val noticeList=ArrayList<Notice>()
            noticeList.clear()
            for (queryDocumentSnapshot in querySnapshot) {
                noticeList.add(Notice(queryDocumentSnapshot["date"].toString(),
                queryDocumentSnapshot["day"].toString(),
                queryDocumentSnapshot["notice"].toString()))
            }
            noticeArrayList.postValue(noticeList)
            noticeProgressBar.postValue(false)
        }.addOnFailureListener {exception ->
            noticeProgressBar.postValue(false)

        }
    }

    fun postNotice(
        date: String,
        day: String,
        notice: String,
        createNotice: CreateNotice
    ) {
        progressBarValue.postValue(true)
        firestore.collection("Notice").add(hashMapOf(
            "date" to date,
            "day" to day,
            "notice" to notice
        )).addOnSuccessListener {document->
            progressBarValue.postValue(false)
            Toast.makeText(createNotice,"Notice added successfully",Toast.LENGTH_LONG).show()
            createNotice.startActivity(Intent(createNotice,NoticeManagement::class.java))
            createNotice.finish()


        }.addOnFailureListener {exception->
            Toast.makeText(createNotice,"Sorry Some Error occured",Toast.LENGTH_LONG).show()
            progressBarValue.postValue(false)
        }

    }

}