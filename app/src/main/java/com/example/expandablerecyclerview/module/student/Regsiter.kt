package com.example.expandablerecyclerview.module.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_regsiter.*

class Regsiter : AppCompatActivity() {
    var mFirebaseAuth=FirebaseAuth.getInstance()
    lateinit var viewModel: StudentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regsiter)
        viewModel=ViewModelProviders.of(this).get(StudentViewModel::class.java)
        setUpEvents()
        setUp()
    }

    private fun setUp() {

    }

    private fun setUpEvents() {
       buttonRegister.setOnClickListener {
           viewModel.registerStudent(this,mFirebaseAuth,it,email.text.toString(),name.text.toString(),hostelName.text.toString(),branchName.text.toString(),roomNumber.text.toString(),password.text.toString())
       }
        viewModel.regsiterProgressBarValue.observe(this, Observer { pvalue->
            if (pvalue){
                registerProgressBar.visibility= View.VISIBLE
            }else{
                registerProgressBar.visibility=View.GONE
            }

        })
    }
}