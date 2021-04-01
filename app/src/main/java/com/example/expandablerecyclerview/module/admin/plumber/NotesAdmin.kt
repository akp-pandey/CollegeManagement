package com.example.expandablerecyclerview.module.admin.plumber

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.expandablerecyclerview.R
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_notes_admin.*

class NotesAdmin : AppCompatActivity() {
    lateinit var fileUri:Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes_admin)
        setUpEvent()

    }

    private fun setUpEvent() {
        choose.setOnClickListener {
            chooseDocument()
        }
        upload.setOnClickListener {
            val name=nameFile.text.toString()
            if (name.isEmpty()){
                Toast.makeText(this,"Name of file cannot be empty",Toast.LENGTH_LONG).show()
            }
            else{
                uploadDocument(name)
            }

        }
    }

    private fun uploadDocument(name: String) {
        notesprogressBar.visibility=View.VISIBLE
       val storageReference:StorageReference=FirebaseStorage.getInstance().reference

        val filePath=storageReference.child(name+"."+"pdf")
        Toast.makeText(this,filePath.name,Toast.LENGTH_LONG).show()
        filePath.putFile(fileUri).addOnSuccessListener {
            notesprogressBar.visibility=View.GONE
            Toast.makeText(this,"NOTES ADDED SUCCESSFULLY",Toast.LENGTH_LONG).show()
            finish()
        }.addOnFailureListener{
            notesprogressBar.visibility=View.GONE
        }
    }

    private fun chooseDocument() {
       var intent=Intent()
        intent.setType("application/pdf")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent,"Choose document"),121)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==121 && resultCode==Activity.RESULT_OK && data!=null){
            fileUri= data.data!!
        }
    }
}