package com.example.expandablerecyclerview.module.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.module.category.CategoryActivity
import com.example.expandablerecyclerview.module.entrypage.EntryPage
import com.google.firebase.auth.FirebaseAuth

class Spalsh : AppCompatActivity() {
    var mFirebaseAuth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh)
        Handler().postDelayed(Runnable {
            if (mFirebaseAuth.currentUser!=null){
                startActivity(Intent(this,CategoryActivity::class.java))
            }else{
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }

        },400)
    }
}