package com.example.expandablerecyclerview.module.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.expandablerecyclerview.R
import com.example.expandablerecyclerview.model.Gallery
import kotlinx.android.synthetic.main.activity_gallery.*
import kotlinx.android.synthetic.main.gallery_layout.view.*

class GalleryActivity : AppCompatActivity() {
    lateinit var viewModel:GalleryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        viewModel=ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        viewModel.getGalleryData()
        setUp()
    }

    private fun setUp() {
        rvGallery.layoutManager=GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false)
        viewModel.galleryList.observe(this, Observer {gallery->
            rvGallery.adapter=GalleryAdapter(gallery)

        })
    }
}

class GalleryAdapter(val imageGallery: ArrayList<Gallery>) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {
    class GalleryViewHolder(val view: View) :RecyclerView.ViewHolder(view) {
        val imageView=view.findViewById<ImageView>(R.id.galleryImage)
        fun bind(gallery: Gallery) {
                Glide.with(view).load(gallery.image).placeholder(R.drawable.load).into(imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.gallery_layout,parent,false)
        return GalleryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageGallery.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(imageGallery[position])
    }

}
