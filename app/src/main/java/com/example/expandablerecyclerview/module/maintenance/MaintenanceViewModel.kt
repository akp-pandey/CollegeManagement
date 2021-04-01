package com.example.expandablerecyclerview.module.maintenance

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class MaintenanceViewModel(application: Application):AndroidViewModel(application) {
    var hostelNumber:String?=null
    val mFirestore:FirebaseFirestore= FirebaseFirestore.getInstance()
    val progressBar:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val eprogressBar:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val cprogressBar:MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }

    fun postPlumberData(
        hostel: String,
        issue: String,
        time: String,
        phone: String,
        plumberAvtivity: PlumberAvtivity,
        currentUser: FirebaseUser?,
        department: String,
        formatted: String
    ) {
            progressBar.postValue(true)
        if (department.equals("Plumber")){
            mFirestore.collection("Plumber").add(
                hashMapOf(
                    "hostel" to hostel,
                    "issue" to issue,
                    "time" to time,
                    "phone" to phone,
                    "dateTime" to formatted
                )
            ).addOnSuccessListener {documentReference ->
                progressBar.postValue(false)
                Toast.makeText(plumberAvtivity,"Compain Successfully Saved",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {exception ->
                progressBar.postValue(false)
                exception.printStackTrace()
            }
        }else if (department.equals("Carpenter")){
            mFirestore.collection("Carpenter").add(
                hashMapOf(
                    "hostel" to hostel,
                    "issue" to issue,
                    "time" to time,
                    "phone" to phone,
                    "dateTime" to formatted
                )
            ).addOnSuccessListener {documentReference ->
                progressBar.postValue(false)
                Toast.makeText(plumberAvtivity,"Compain Successfully Saved",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {exception ->
                progressBar.postValue(false)
                exception.printStackTrace()
            }

        }else if (department.equals("Electrician")){
            mFirestore.collection("Electrician").add(
                hashMapOf(
                    "hostel" to hostel,
                    "issue" to issue,
                    "time" to time,
                    "phone" to phone,
                    "dateTime" to formatted
                )
            ).addOnSuccessListener {documentReference ->
                progressBar.postValue(false)
                Toast.makeText(plumberAvtivity,"Compain Successfully Saved",Toast.LENGTH_LONG).show()
            }.addOnFailureListener {exception ->
                progressBar.postValue(false)
                exception.printStackTrace()
            }

        }


        plumberAvtivity.finish()
    }

}
