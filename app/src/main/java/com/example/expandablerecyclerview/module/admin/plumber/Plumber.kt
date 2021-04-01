package com.example.expandablerecyclerview.module.admin.plumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.model.Complain
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_plumber.*

class Plumber : AppCompatActivity() {
    lateinit var viewModel: AdminViewModel
    var firebaseFirestore:FirebaseFirestore= FirebaseFirestore.getInstance()
    lateinit var plumberAdapter:PlumberComplainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plumber)
        viewModel=ViewModelProviders.of(this).get(AdminViewModel::class.java)
        setUpEvents()
        observerData()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        viewModel.plumberComplainList.observe(this, Observer {complain->
                if (complain.isEmpty()){
                    linearLayout.visibility=View.VISIBLE
                }else {
                    linearLayout.visibility=View.GONE
                }
               plumberAdapter.submitList(complain)
               rvPlumberComplain.adapter=plumberAdapter
               plumberAdapter.notifyDataSetChanged()




        })
    }

    private fun observerData() {
        plumberAdapter=PlumberComplainAdapter{complain->
            firebaseFirestore.collection("Plumber").get().addOnSuccessListener {result->
               result.forEach{value->
                   if (value.get("phone").toString().equals(complain.activePhone))
                   {
                       val id=value.id
                       firebaseFirestore.collection("Plumber").document(id).delete()
                       viewModel.getPlumberComplain()

                   }
               }
            }
        }


        viewModel.ppbar.observe(this, Observer {value->
            if (value){
                val mView=findViewById<ImageView>(R.id.plumberImage)
              Glide.with(this).load(R.raw.load).into(mView)

            }else{
                plumberImage.visibility=View.GONE
            }

        })
    }


    private fun setUpEvents() {
        viewModel.getPlumberComplain()
    }
}