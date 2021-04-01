package com.example.expandablerecyclerview.module.faculty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.expandablerecyclerview.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class FacultyManagement : AppCompatActivity() {
    var storageRefrence:StorageReference=FirebaseStorage.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty_management)
        setUp()
    }

    private fun setUp() {
        Log.d("the length is",storageRefrence.bucket.length.toString())
        storageRefrence.storage.reference.getStream { state, stream ->

        }
    }
}