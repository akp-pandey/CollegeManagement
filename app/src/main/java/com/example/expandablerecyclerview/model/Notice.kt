package com.example.expandablerecyclerview.model

import androidx.recyclerview.widget.DiffUtil

data class Notice(val date:String,val day:String,val notice:String)

object NoticeDiff:DiffUtil.ItemCallback<Notice>(){
    override fun areItemsTheSame(oldItem: Notice, newItem: Notice): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Notice, newItem: Notice): Boolean {
        return oldItem.equals(newItem)
    }

}