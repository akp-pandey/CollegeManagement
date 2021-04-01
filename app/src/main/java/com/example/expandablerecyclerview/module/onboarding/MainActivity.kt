package com.example.expandablerecyclerview.module.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.model.getViewPagerData
import com.example.expandablerecyclerview.module.category.CategoryActivity
import com.example.expandablerecyclerview.module.entrypage.EntryPage
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var mFirebaseAuth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (mFirebaseAuth.currentUser!=null){
            startActivity(Intent(this,CategoryActivity::class.java))
        }
        setUp()

  }

    private fun setUp() {
        viewPager2.adapter=
            MyViewPagerAdapter(
                getViewPagerData()
            )
        getStarted.setOnClickListener {
            startActivity(Intent(this,EntryPage::class.java))
        }
    }


}