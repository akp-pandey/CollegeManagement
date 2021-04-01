package com.example.expandablerecyclerview.module.admin.plumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import kotlinx.android.synthetic.main.activity_admin_login.*

class AdminLogin : AppCompatActivity() {
    lateinit var viewModel: AdminViewModel
    val user= arrayOf("FacultyAdmin","PlumberAdmin","ElectricianAdmin","CarpenterAdmin","NoticeAdmin")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)
        viewModel=ViewModelProviders.of(this).get(AdminViewModel::class.java)
        setUp()
        setUpEvents()
    }

    private fun setUpEvents() {
        val adapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,user)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter=adapter
        adminbuttonLogin.setOnClickListener {
            viewModel.loginAdmin(adminEmail.text.toString(),adminPassword.text.toString(),spinner.selectedItem.toString(),this,it)
        }

    }

    private fun setUp() {
            viewModel.adminLoginProgressBar.observe(this, Observer {value->
                if (value){
                    adminloginProgressBar.visibility=View.VISIBLE
                }else{
                    adminloginProgressBar.visibility=View.GONE
                }

            })
    }

}