package com.example.lifeshare_android.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView
import com.example.lifeshare_android.R

import com.example.lifeshare_android.model.admin.ApplyList
import com.example.lifeshare_android.widget.recyclerview.navigator.AdminAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.viewholder.AdminViewHolder

class AdminMainAdapter : RecyclerView.Adapter<AdminViewHolder>(), AdminAdapterNavigator {

    private lateinit var applyList: List<ApplyList>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        return AdminViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_admin_main, parent, false))
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        holder.setNavigator(this)
        holder.bind(applyList[position])
    }

    override fun getItemCount(): Int {
        return if(::applyList.isInitialized) applyList.size else 0
    }

    fun updateList(applyList: List<ApplyList>?) {
        this.applyList = applyList!!
        notifyDataSetChanged()
    }
}