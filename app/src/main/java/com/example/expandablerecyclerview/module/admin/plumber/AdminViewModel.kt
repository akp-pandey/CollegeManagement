package com.example.expandablerecyclerview.module.admin.plumber

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.expandablerecyclerview.model.Complain
import com.example.expandablerecyclerview.module.faculty.FacultyManagement
import com.example.expandablerecyclerview.module.maintenance.ElectricianManagement
import com.example.expandablerecyclerview.module.maintenance.PlumberAvtivity
import com.example.expandablerecyclerview.module.notice.NoticeManagement
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore

class AdminViewModel(application: Application):AndroidViewModel(application) {
    val firestore:FirebaseFirestore= FirebaseFirestore.getInstance()
    val plumberComplainList by lazy { MutableLiveData<ArrayList<Complain>>() }
    val ppbar by lazy { MutableLiveData<Boolean>() }
    val epbar by lazy { MutableLiveData<Boolean>()}
    val electricianComplainList by lazy { MutableLiveData<ArrayList<Complain>>() }
    val adminLoginProgressBar by lazy { MutableLiveData<Boolean>() }
    val cpbar by lazy { MutableLiveData<Boolean>() }
    val carpenterComplainList by lazy { MutableLiveData<ArrayList<Complain>>() }
    fun getPlumberComplain() {
        ppbar.postValue(true)
        firestore.collection("Plumber").get().addOnSuccessListener {result->
            val arrayListData=ArrayList<Complain>()
           result.forEach {document->
               arrayListData.add(Complain(document["hostel"].toString(),document["issue"].toString(),document["time"].toString(),document["phone"].toString(),document["dateTime"].toString()))
           }
            plumberComplainList.postValue(arrayListData)
            ppbar.postValue(false)
        }.addOnFailureListener {exception ->
            exception.printStackTrace()
            ppbar.postValue(false)
        }
    }
    fun getElectricianCompain(){
        epbar.postValue(true)
        firestore.collection("Electrician").get().addOnSuccessListener { result->
            val arrayList=ArrayList<Complain>()
            result.forEach {document->
                arrayList.add(Complain(document["hostel"].toString(),document["issue"].toString(),document["time"].toString(),document["phone"].toString(),document["dateTime"].toString()))
            }
            electricianComplainList.postValue(arrayList)
            epbar.postValue(false)
        }.addOnFailureListener {
            it.printStackTrace()
            epbar.postValue(false)
        }
    }

    fun getCarpenterComplain() {
        cpbar.postValue(true)
        firestore.collection("Carpenter").get().addOnSuccessListener {result->
            val arrayListData=ArrayList<Complain>()
            result.forEach {document->
                arrayListData.add(Complain(document["hostel"].toString(),document["issue"].toString(),document["time"].toString(),document["phone"].toString(),document["dateTime"].toString()))
            }
            carpenterComplainList.postValue(arrayListData)
            cpbar.postValue(false)
        }.addOnFailureListener {exception ->
            exception.printStackTrace()
            cpbar.postValue(false)
        }
    }

    fun loginAdmin(
        email: String,
        password: String,
        role: String,
        admin: AdminLogin,
        it: View
    ) {
        adminLoginProgressBar.postValue(true)
        if (role.equals("FacultyAdmin")){

            firestore.collection("FacultyAdmin").get().addOnCompleteListener {result->
                result.addOnSuccessListener {document->
                    document.forEach {value->
                        if (value["username"].toString().equals(email) && value["password"].toString().equals(password) ){
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Successfully login",Snackbar.LENGTH_LONG).show()
                            admin.finish()
                            admin.startActivity(Intent(admin, NotesAdmin::class.java))
                        }else{
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Invalid credentials",Snackbar.LENGTH_LONG).show()
                        }
                    }

                }

            }

        }else if (role.equals("PlumberAdmin")){

            firestore.collection("PlumberAdmin").get().addOnCompleteListener {result->
                result.addOnSuccessListener {document->
                    document.forEach {value->
                        if (value["username"].toString().equals(email) && value["password"].toString().equals(password) ){
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Successfully login",Snackbar.LENGTH_LONG).show()
                            admin.finish()
                            admin.startActivity(Intent(admin,Plumber::class.java))
                        }else{
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Invalid credentials",Snackbar.LENGTH_LONG).show()
                        }
                    }

                }

            }

        }else if (role.equals("ElectricianAdmin")){

            firestore.collection("ElectricianAdmin").get().addOnCompleteListener {result->
                result.addOnSuccessListener {document->
                    document.forEach {value->
                        if (value["username"].toString().equals(email) && value["password"].toString().equals(password) ){
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Successfully login",Snackbar.LENGTH_LONG).show()
                            admin.finish()
                            admin.startActivity(Intent(admin, Electrician::class.java))
                        }else{
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Invalid credentials",Snackbar.LENGTH_LONG).show()
                        }
                    }

                }

            }

        }else if (role.equals("CarpenterAdmin")){

            firestore.collection("CarpenterAdmin").get().addOnCompleteListener {result->
                result.addOnSuccessListener {document->
                    document.forEach {value->
                        if (value["username"].toString().equals(email) && value["password"].toString().equals(password) ){
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Successfully login",Snackbar.LENGTH_LONG).show()
                            admin.finish()
                            admin.startActivity(Intent(admin,CarpenterManagement::class.java))
                        }else{
                            adminLoginProgressBar.postValue(false)
                            Snackbar.make(it,"Invalid credentials",Snackbar.LENGTH_LONG).show()
                        }
                    }

                }

            }

        }else if (role.equals("NoticeAdmin")){
                firestore.collection("NoticeAdmin").get().addOnCompleteListener {result->
                    result.addOnSuccessListener {document->
                        document.forEach {value->
                            if (value["username"].toString().equals(email) && value["password"].toString().equals(password) ){
                                adminLoginProgressBar.postValue(false)
                                Snackbar.make(it,"Successfully login",Snackbar.LENGTH_LONG).show()
                                admin.finish()
                                admin.startActivity(Intent(admin,NoticeManagement::class.java))
                            }else{
                                adminLoginProgressBar.postValue(false)
                                Snackbar.make(it,"Invalid credentials",Snackbar.LENGTH_LONG).show()
                            }
                        }

                    }

                }
        }

    }


}