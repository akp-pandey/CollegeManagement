package com.example.expandablerecyclerview.module.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {
    var mFirebaseAuth:FirebaseAuth= FirebaseAuth.getInstance()
    lateinit var viewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        viewModel=ViewModelProviders.of(this).get(StudentViewModel::class.java)
            setUpEvents()
            setUp()
            observeData()

    }

    private fun observeData() {
        viewModel.loginProgressBarValue.observe(this, Observer {value->
            if (value){
                loginProgressBar.visibility=View.VISIBLE
            }else{
                loginProgressBar.visibility=View.GONE
            }

        })
    }

    private fun setUp() {
        buttonLogin.setOnClickListener {
            viewModel.loginUser(userEmail.text.toString(),userPassword.text.toString(),it,this,mFirebaseAuth)

        }

    }

    private fun setUpEvents() {
        register.setOnClickListener {
            startActivity(Intent(this,Regsiter::class.java))
        }
    }


}