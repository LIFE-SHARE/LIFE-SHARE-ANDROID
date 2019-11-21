package com.example.lifeshare_android.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView

import com.example.lifeshare_android.R
import com.example.lifeshare_android.model.house.House
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.navigator.HouseAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.viewholder.HouseViewHolder

class HouseAdapter : RecyclerView.Adapter<HouseViewHolder>(), HouseAdapterNavigator {

    val openHouse = SingleLiveEvent<Int>()

    private lateinit var homeList: List<House>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        return HouseViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_house, parent, false))
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        holder.setNavigator(this)
        holder.bind(homeList[position])
    }

    override fun getItemCount(): Int {
        return if(::homeList.isInitialized) homeList.size else 0
    }

    fun updateList(houseList: List<House>?) {
        this.homeList = houseList!!
        notifyDataSetChanged()
    }

    override fun openHouse(id: Int) {
        openHouse.value = id
    }
}