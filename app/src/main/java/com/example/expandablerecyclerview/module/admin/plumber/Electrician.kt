package com.example.expandablerecyclerview.module.admin.plumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.expandablerecyclerview.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_electrician.*

class Electrician : AppCompatActivity() {
    lateinit var viewModel: AdminViewModel
    lateinit var electricianAdapter:ElectricianAdapter
    var firebaseFirestore=FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_electrician)
        viewModel=ViewModelProviders.of(this).get(AdminViewModel::class.java)
        setUpEvents()
        observeData()
    }

    private fun observeData() {
        viewModel.epbar.observe(this, Observer {
            if (it){
                electricianImage.visibility=View.VISIBLE
            }else{
                electricianImage.visibility=View.GONE
            }
        })
        viewModel.electricianComplainList.observe(this, Observer { complainList->
            if (complainList.isEmpty()){
                linearLayout.visibility= View.VISIBLE
            }else{
                linearLayout.visibility= View.GONE
            }
           electricianAdapter.submitList(complainList)
            rvElectricianComplain.adapter=electricianAdapter
            electricianAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpEvents() {
        viewModel.getElectricianCompain()
        electricianAdapter= ElectricianAdapter { complain ->
            firebaseFirestore.collection("Electrician").get().addOnSuccessListener {result->
                result.forEach{value->
                    if (value.get("phone").toString().equals(complain.activePhone))
                    {
                        val id=value.id
                        firebaseFirestore.collection("Electrician").document(id).delete()
                        viewModel.getElectricianCompain()

                    }
                }
            }
        }
    }
}