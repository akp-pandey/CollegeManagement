package com.example.expandablerecyclerview.module.entrypage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.module.admin.plumber.AdminLogin
import com.example.expandablerecyclerview.module.category.CategoryActivity
import com.example.expandablerecyclerview.module.student.Login
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_entry_page.*

class EntryPage : AppCompatActivity() {
    var firebaseAuth: FirebaseAuth= FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry_page)
        if (firebaseAuth.currentUser!=null){
            student.isEnabled=false
            startActivity(Intent(this,CategoryActivity::class.java))
        }
        setUpAction()
    }

    private fun setUpAction() {
        student.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
        admin.setOnClickListener {
            startActivity(Intent(this,AdminLogin::class.java))
        }
    }
}