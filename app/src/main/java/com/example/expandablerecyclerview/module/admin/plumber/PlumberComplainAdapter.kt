package com.example.expandablerecyclerview.module.admin.plumber

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.PlumberLayoutBinding
import com.example.expandablerecyclerview.model.Complain
import com.example.expandablerecyclerview.model.ComplainDiff
import kotlinx.android.synthetic.main.plumber_layout.view.*

class PlumberComplainAdapter(private val onclick: (Complain) -> Unit) : ListAdapter<Complain,PlumberViewHolder>(ComplainDiff) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlumberViewHolder {
        val binding=PlumberLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder=PlumberViewHolder(binding)
        holder.itemView.delete.setOnClickListener {
                onclick(getItem(holder.adapterPosition))
        }
        return holder
    }


    override fun onBindViewHolder(holder: PlumberViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}
class PlumberViewHolder(val binding: PlumberLayoutBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(item: Complain?) {
            binding.run {
                this.complain=item
                executePendingBindings()
            }
    }


}
