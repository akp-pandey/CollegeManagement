package com.example.expandablerecyclerview.module.notice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.DeleteNoticeLayoutBinding
import com.example.expandablerecyclerview.model.Notice
import com.example.expandablerecyclerview.model.NoticeDiff
import kotlinx.android.synthetic.main.delete_notice_layout.view.*

class MyDeleteAdapter(private val onClick:(Notice) ->Unit):ListAdapter<Notice,NoticeViewHolder>(NoticeDiff) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeViewHolder {
        val binding=DeleteNoticeLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val holder=NoticeViewHolder(binding)
        holder.itemView.deleteNotice.setOnClickListener{
            onClick(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: NoticeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class NoticeViewHolder(val binding: DeleteNoticeLayoutBinding) :RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Notice?) {
            binding.run {
                this.notice=item
                executePendingBindings()
            }
    }

}
