package com.example.lifeshare_android.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseItemViewModel
import com.example.lifeshare_android.model.room.Room
import com.example.lifeshare_android.util.Strings
import com.example.lifeshare_android.widget.recyclerview.navigator.RoomItemNavigator

class RoomItemViewModel : BaseItemViewModel<Room, RoomItemNavigator>() {

    val image = MutableLiveData<String>()
    val peopleCount = MutableLiveData<String>()
    val depositAndMonthly = MutableLiveData<String>()

    fun openRoom() {
        getNavigator().openRoom()
    }

    override fun bind(data: Room) {
        image.value = Strings.MAIN_HOST + "/" + data.imageData
        peopleCount.value = String.format("" + data.peopleCnt + "인실")
        depositAndMonthly.value = data.money
    }
}