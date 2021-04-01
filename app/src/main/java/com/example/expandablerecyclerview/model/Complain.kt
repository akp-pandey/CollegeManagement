package com.example.expandablerecyclerview.model

import androidx.recyclerview.widget.DiffUtil

data class Complain(val hostelName:String,val issue:String,val availableTime:String,val activePhone:String,val dateTime:String )
object ComplainDiff:DiffUtil.ItemCallback<Complain>(){
    override fun areItemsTheSame(oldItem: Complain, newItem: Complain): Boolean {
        return oldItem==newItem
    }

    override fun areContentsTheSame(oldItem: Complain, newItem: Complain): Boolean {
        return oldItem.equals(newItem)
    }

}