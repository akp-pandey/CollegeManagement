package com.example.expandablerecyclerview.module.admin.plumber

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.PlumberLayoutBinding
import com.example.expandablerecyclerview.model.Complain
import com.example.expandablerecyclerview.model.ComplainDiff
import kotlinx.android.synthetic.main.plumber_layout.view.*

class ElectricianAdapter(private val onClick:(Complain)->Unit):ListAdapter<Complain,ElectricianAdapter.ElectricainViewHolder>(ComplainDiff) {
    class ElectricainViewHolder(val binding: PlumberLayoutBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Complain?) {
                binding.run {
                    this.complain=item
                    executePendingBindings()
                }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectricainViewHolder {
        val binding=PlumberLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder=ElectricainViewHolder(binding)
        holder.itemView.delete.setOnClickListener {
            onClick(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ElectricainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
