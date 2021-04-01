package com.example.expandablerecyclerview.module.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.databinding.ActivityCategory2Binding
import com.example.expandablerecyclerview.module.attendance.AttendanceActivity
import com.example.expandablerecyclerview.module.connectivity.ConnectivityLiveData
import com.example.expandablerecyclerview.module.results.ResultActivity
import com.example.expandablerecyclerview.module.faculty.FacultyManagement
import com.example.expandablerecyclerview.module.gallery.GalleryActivity
import com.example.expandablerecyclerview.module.maintenance.PlumberAvtivity
import com.example.expandablerecyclerview.module.notice.NoticeActivity
import com.example.expandablerecyclerview.module.student.Login
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_category2.*

class CategoryActivity : AppCompatActivity() {
    var mFirebaseAuth=FirebaseAuth.getInstance()
    lateinit var connectivityLiveData: ConnectivityLiveData
    lateinit var categoryViewModel: CategoryViewModel
    lateinit var binding:ActivityCategory2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_category2)
        //ViewModel Instance
        categoryViewModel=ViewModelProviders.of(this).get(CategoryViewModel::class.java)
        connectivityLiveData= ConnectivityLiveData(this)
        connectivityLiveData.observe(this, Observer {value->
            if (!value){
              Toast.makeText(this,"SORRY NO INTERNET CONNECTION FOUND",Toast.LENGTH_LONG).show()
            }

        })

        binding.vObj=categoryViewModel
        setUpAction()
        getData()


    }

    private fun getData() {
        categoryViewModel.currentUserData(this,mFirebaseAuth)
        categoryViewModel.student.observe(this, Observer {student->
                nameDetail.setText("Name:- "+student.name)
                branchDetail.setText("Branch:- "+student.branch)
                roomDetail.setText("Room No:- "+student.room)
                hostelDetail.setText("Hostel:- "+student.hostel)
                welcomeText.setText("Welcome, "+student.name)


        })
    }

    private fun setUpAction() {
        binding.maintenanceDepartment.setOnClickListener {
            startActivity(Intent(this,PlumberAvtivity::class.java))
        }
        binding.noticeBoard.setOnClickListener {
            finish()
            startActivity(Intent(this,NoticeActivity::class.java))

        }
        binding.attendance.setOnClickListener {
            finish()
            startActivity(Intent(this,
                AttendanceActivity::class.java))

        }
        binding.logout.setOnClickListener {
            mFirebaseAuth.signOut()
            startActivity(Intent(this,Login::class.java))
            Toast.makeText(this,"Logout succesfully",Toast.LENGTH_LONG).show()
            finish()


        }
        binding.gallery.setOnClickListener {
            startActivity(Intent(this,GalleryActivity::class.java))
        }
        binding.notes.setOnClickListener {
            startActivity(Intent(this,FacultyManagement::class.java))
        }
        binding.results.setOnClickListener {
            startActivity(Intent(this,
                ResultActivity::class.java))
        }
    }
}