package com.example.lifeshare_android.widget.recyclerview.viewholder

import com.example.lifeshare_android.base.BaseViewHolder
import com.example.lifeshare_android.databinding.ItemRoomBinding
import com.example.lifeshare_android.model.room.Room
import com.example.lifeshare_android.widget.recyclerview.navigator.room.RoomAdapterNavigator
import com.example.lifeshare_android.widget.recyclerview.navigator.room.RoomItemNavigator
import com.example.lifeshare_android.widget.recyclerview.viewmodel.RoomItemViewModel

class RoomViewHolder(val binding: ItemRoomBinding) : BaseViewHolder<RoomAdapterNavigator>(binding.root),
    RoomItemNavigator {

    private val viewModel = RoomItemViewModel()

    private lateinit var room: Room

    fun bind(data: Room) {
        viewModel.bind(data)
        room = data
        viewModel.setNavigator(this)
        binding.viewModel = viewModel
    }

    override fun openRoom() {
        getNavigator().openRoom(room.id)
    }
}