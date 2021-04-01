package com.example.expandablerecyclerview.module.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.NoticeLayoutBinding
import com.example.expandablerecyclerview.model.Notice

class NoticeAdapter(val notice: ArrayList<Notice>) : RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder>() {
    class NoticeViewHolder(val binding: NoticeLayoutBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(notice: Notice) {
            binding.notice=notice
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding=NoticeLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoticeViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return notice.size
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(notice[position])
    }

}
