package com.example.lifeshare_android.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseItemViewModel
import com.example.lifeshare_android.model.house.House
import com.example.lifeshare_android.util.Constants
import com.example.lifeshare_android.widget.recyclerview.navigator.house.HouseItemNavigator

class HouseItemViewModel : BaseItemViewModel<House, HouseItemNavigator>() {

    val houseImage = MutableLiveData<String>()
    val houseName = MutableLiveData<String>()
    val houseAddress = MutableLiveData<String>()

    fun openHouse() {
        getNavigator().openHouse()
    }

    override fun bind(data: House) {
        houseImage.value = Constants.MAIN_HOST + "/" + data.imageData
        houseName.value = data.name
        houseAddress.value = data.address
    }
}