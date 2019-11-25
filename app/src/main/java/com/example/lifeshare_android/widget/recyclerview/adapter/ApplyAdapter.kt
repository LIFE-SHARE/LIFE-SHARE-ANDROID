package com.example.lifeshare_android.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView

import com.example.lifeshare_android.R
import com.example.lifeshare_android.model.apply.Apply
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.navigator.apply.ApplyAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.viewholder.ApplyViewHolder

class ApplyAdapter : RecyclerView.Adapter<ApplyViewHolder>(),
    ApplyAdapterNavigator {

    val onClickAcceptEvent = SingleLiveEvent<Int>()
    val onClickRefusalEvent = SingleLiveEvent<Int>()

    private lateinit var applyList: List<Apply>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplyViewHolder {
        return ApplyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_apply, parent, false))
    }

    override fun onBindViewHolder(holder: ApplyViewHolder, position: Int) {
        holder.setNavigator(this)
        holder.bind(applyList[position])
    }

    override fun getItemCount(): Int {
        return if(::applyList.isInitialized) applyList.size else 0
    }

    fun updateList(applyList: List<Apply>?) {
        this.applyList = applyList!!
        notifyDataSetChanged()
    }

    override fun onClickAccept(id: Int) {
        onClickAcceptEvent.value = id
    }

    override fun onClickRefusal(id: Int) {
        onClickRefusalEvent.value = id
    }
}