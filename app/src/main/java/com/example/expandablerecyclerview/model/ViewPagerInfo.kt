package com.example.expandablerecyclerview.model

import com.example.expandablerecyclerview.R

data class ViewPagerInfo(val image:Int,val title:String,val  desription:String)

fun getViewPagerData():ArrayList<ViewPagerInfo>{
    return arrayListOf(
        ViewPagerInfo(R.drawable.ic_file,"Notice","Hello Gietians,get the notice of your college under fingertip"),
        ViewPagerInfo(R.drawable.ic_immigration,"Attendance","Hello Gietians, check your attendance within fraction of seconds"),
        ViewPagerInfo(R.drawable.ic_writing,"Notes","Here you will get the notes of subject uploaded by your college faculty"),
        ViewPagerInfo(R.drawable.ic_bad_review,"Maintenance Department","Most convenient way to make complain to maintenance department"),
        ViewPagerInfo(R.drawable.ic_management,"Admin","Admin can also use this to access and upload the information")

    )
}