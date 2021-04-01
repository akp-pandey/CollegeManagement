package com.example.expandablerecyclerview.module.gallery

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.expandablerecyclerview.model.Gallery
import com.google.firebase.firestore.FirebaseFirestore

class GalleryViewModel(application: Application):AndroidViewModel(application) {
    val galleryList by lazy { MutableLiveData<ArrayList<Gallery>>() }
    var firestore=FirebaseFirestore.getInstance()

    fun getGalleryData(){
        firestore.collection("Gallery").get().addOnSuccessListener {result->
            var arrayList=ArrayList<Gallery>()
            result.forEach {document->
                arrayList.add(Gallery(document["image"].toString()))

            }
            galleryList.postValue(arrayList)
        }
    }
}