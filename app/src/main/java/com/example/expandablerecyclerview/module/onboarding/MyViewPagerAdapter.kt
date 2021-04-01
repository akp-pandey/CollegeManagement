package com.example.expandablerecyclerview.module.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expandablerecyclerview.databinding.ViewPagerLayoutBinding
import com.example.expandablerecyclerview.model.ViewPagerInfo

class MyViewPagerAdapter(val viewPagerData: ArrayList<ViewPagerInfo>) : RecyclerView.Adapter<MyViewPagerAdapter.ViewPagerViewHolder>() {
    class ViewPagerViewHolder(val view: ViewPagerLayoutBinding) :RecyclerView.ViewHolder(view.root){
        fun bind(viewPagerInfo: ViewPagerInfo) {
                    view.run {
                        this.viewPager=viewPagerInfo
                        this.imageView2.setImageResource(viewPagerInfo.image)
                    }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view=ViewPagerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewPagerViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return viewPagerData.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(viewPagerData[position])
        if (position==0){
            holder.view.backArrow.visibility=View.GONE
            holder.view.forwardArrow.visibility=View.VISIBLE
        }else if (position==(viewPagerData.size-1))
        {
            holder.view.backArrow.visibility=View.VISIBLE
            holder.view.forwardArrow.visibility=View.GONE
        }else{
            holder.view.backArrow.visibility=View.VISIBLE
            holder.view.forwardArrow.visibility=View.VISIBLE
        }
    }

}
