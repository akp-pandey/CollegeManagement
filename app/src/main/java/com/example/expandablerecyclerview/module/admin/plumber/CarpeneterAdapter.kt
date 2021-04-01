package com.example.expandablerecyclerview.module.admin.plumber

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.PlumberLayoutBinding
import com.example.expandablerecyclerview.model.Complain
import com.example.expandablerecyclerview.model.ComplainDiff
import kotlinx.android.synthetic.main.plumber_layout.view.*

class CarpeneterAdapter(private val onclick: (Complain) -> Unit):ListAdapter<Complain,CarpeneterAdapter.CarpenterViewHolder>(ComplainDiff) {
    class CarpenterViewHolder(val binding: PlumberLayoutBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Complain) {
            binding.run {
                this.complain=item
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarpenterViewHolder {
        val binding= PlumberLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder=CarpenterViewHolder(binding)
        holder.itemView.delete.setOnClickListener {
            onclick(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: CarpenterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
