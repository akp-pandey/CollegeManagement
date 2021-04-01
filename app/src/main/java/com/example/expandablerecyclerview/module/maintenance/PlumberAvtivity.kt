package com.example.expandablerecyclerview.module.maintenance

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_plumber_avtivity.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PlumberAvtivity : AppCompatActivity() {
    val department= arrayOf("Plumber","Carpenter","Electrician")
    lateinit var viewModel: MaintenanceViewModel
    var firebaseAuth:FirebaseAuth= FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plumber_avtivity)
        viewModel=ViewModelProviders.of(this).get(MaintenanceViewModel::class.java)
        setUp()
        setUpAction()
        observingValue()
    }

    private fun setUp() {
        val adapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,department)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        departmentSpinner.adapter=adapter
    }

    private fun observingValue() {
        viewModel.progressBar.observe(this, Observer {value->
            if (value){
                pbar.visibility=View.VISIBLE
            }else{
                pbar.visibility=View.GONE
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun setUpAction() {
        submitPlumber.setOnClickListener {
            val current=LocalDateTime.now()
            val formatter=DateTimeFormatter.ofPattern("dd--MM-yyyy HH:mm:ss.SSS")
            val formatted=current.format(formatter)
            viewModel.postPlumberData(hostel.text.toString(),issue.text.toString(),time.text.toString(),room.text.toString(),this,firebaseAuth.currentUser,departmentSpinner.selectedItem.toString(),formatted)
            finish()

        }
    }


}