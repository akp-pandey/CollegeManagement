package com.example.expandablerecyclerview.module.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.expandablerecyclerview.model.Student
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CategoryViewModel(application: Application):AndroidViewModel(application) {
    val firebaseFirestore=FirebaseFirestore.getInstance()
    val student by lazy { MutableLiveData<Student>() }
    fun currentUserData(categoryActivity: CategoryActivity, mFirebaseAuth: FirebaseAuth) {
        val id=mFirebaseAuth.currentUser.uid
        firebaseFirestore.collection("Student").get().addOnCompleteListener {result->
            result.addOnSuccessListener {value->
                value.forEach {document->
                    if (document["id"].toString().equals(id)){
                        student.postValue(Student(id,document["hostel"].toString(),document["room"].toString(),
                        document["branch"].toString(),document["name"].toString()))
                    }

                }

            }

        }

    }

}