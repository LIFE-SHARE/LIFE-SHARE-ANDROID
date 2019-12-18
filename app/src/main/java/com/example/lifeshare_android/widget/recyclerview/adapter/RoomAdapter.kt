package com.example.lifeshare_android.widget.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil

import androidx.recyclerview.widget.RecyclerView

import com.example.lifeshare_android.R
import com.example.lifeshare_android.model.room.Room
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.navigator.room.RoomAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.viewholder.RoomViewHolder

class RoomAdapter : RecyclerView.Adapter<RoomViewHolder>(), RoomAdapterNavigator {

    val openRoom = SingleLiveEvent<Int>()

    private lateinit var roomList: List<Room>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_room, parent, false))
    }

    override fun getItemCount(): Int {
        return if(::roomList.isInitialized) roomList.size else 0
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.setNavigator(this)
        holder.bind(roomList[position])
    }

    fun updateList(roomList: List<Room>?) {
        this.roomList = roomList!!
        notifyDataSetChanged()
    }

    override fun openRoom(id: Int) {
        openRoom.value = id
    }
}