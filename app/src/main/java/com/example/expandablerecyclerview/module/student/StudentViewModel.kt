package com.example.expandablerecyclerview.module.student

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.expandablerecyclerview.module.category.CategoryActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.logging.Handler

class StudentViewModel(application: Application):AndroidViewModel(application) {
    val firebaseFirestore=FirebaseFirestore.getInstance()
    val regsiterProgressBarValue by lazy { MutableLiveData<Boolean>() }
    val loginProgressBarValue by lazy { MutableLiveData<Boolean>() }
    fun registerStudent(
        registerActivity: Regsiter,
        mFirebaseAuth: FirebaseAuth,
        register: View,
        email: String,
        name: String,
        hostel: String,
        branch: String,
        room: String,
        password: String
    ) {
        if (email.isNotEmpty() && name.isNotEmpty() && hostel.isNotEmpty() && branch.isNotEmpty() && room.isNotEmpty() && password.isNotEmpty()){
            regsiterProgressBarValue.postValue(true)
            mFirebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(registerActivity,object:OnCompleteListener<AuthResult>{
                    override fun onComplete(p0: Task<AuthResult>) {
                        if (p0.isSuccessful){
                            regsiterProgressBarValue.postValue(false)
                            Snackbar.make(register,"Account created !! ",Snackbar.LENGTH_LONG).show()
                            val id=mFirebaseAuth.currentUser.uid
                            firebaseFirestore.collection("Student").add(
                                hashMapOf(
                                    "id" to id,
                                    "email" to email,
                                    "password" to password,
                                    "name" to name,
                                    "hostel" to hostel,
                                    "room" to room,
                                    "branch" to branch
                                )
                            )


                            registerActivity.finish()
                            registerActivity.startActivity(Intent(registerActivity,Login::class.java))

                        }else{
                            regsiterProgressBarValue.postValue(false)

                            Snackbar.make(register,"Some error occured !! ",Snackbar.LENGTH_LONG).show()
                        }
                    }

                })
        }else{
            Snackbar.make(register,"All feilds are required",Snackbar.LENGTH_LONG).show()
        }


            }

    fun loginUser(
        userName: String,
        userPassword: String,
        it: View,
        login: Login,
        mFirebaseAuth: FirebaseAuth
    ) {
        if (userName.isNotEmpty() && userPassword.isNotEmpty()){
            loginProgressBarValue.postValue(true)
            mFirebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(login,object :OnCompleteListener<AuthResult>{
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful){
                        loginProgressBarValue.postValue(false)
                        Snackbar.make(it,"Successfully Login",Snackbar.LENGTH_LONG).show()
                        login.finish()
                        login.startActivity(Intent(login,CategoryActivity::class.java))


                    }else{
                        loginProgressBarValue.postValue(false)
                        Snackbar.make(it,"Invalid Username/Password",Snackbar.LENGTH_LONG).show()
                    }
                }

            })
        }else{
            Snackbar.make(it,"Username/Password Cannot be empty",Snackbar.LENGTH_LONG).show()
        }

    }


}