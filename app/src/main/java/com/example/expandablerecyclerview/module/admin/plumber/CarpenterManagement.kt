package com.example.expandablerecyclerview.module.admin.plumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_carpenter_management.*
import kotlinx.android.synthetic.main.activity_carpenter_management.linearLayout
import kotlinx.android.synthetic.main.activity_plumber.*

class CarpenterManagement : AppCompatActivity() {
    lateinit var viewModel: AdminViewModel
    lateinit var carpenterAdapter:CarpeneterAdapter
    var firebaseFirestore=FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carpenter_management)
        viewModel=ViewModelProviders.of(this).get(AdminViewModel::class.java)
        setUpEvents()
        observeData()
    }

    private fun observeData() {
        viewModel.cpbar.observe(this, Observer {
            if (it){
                carpenterImage.visibility=View.VISIBLE
            }else{
                carpenterImage.visibility=View.GONE
            }
        })
        viewModel.carpenterComplainList.observe(this, Observer {complainList->
            if (complainList.isEmpty()){
                linearLayout.visibility= View.VISIBLE
            }else {
                linearLayout.visibility= View.GONE
            }
            carpenterAdapter.submitList(complainList)
            rvCarpenterComplain.adapter=carpenterAdapter
            carpenterAdapter.notifyDataSetChanged()

        })
    }

    private fun setUpEvents() {
        viewModel.getCarpenterComplain()
        carpenterAdapter= CarpeneterAdapter { complain ->
            firebaseFirestore.collection("Carpenter").get().addOnSuccessListener {result->
                result.forEach{value->
                    if (value.get("phone").toString().equals(complain.activePhone))
                    {
                        val id=value.id
                        firebaseFirestore.collection("Carpenter").document(id).delete()
                        viewModel.getCarpenterComplain()

                    }
                }
            }

        }

    }
}
