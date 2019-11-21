package com.example.lifeshare_android.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.model.house.House
import com.example.lifeshare_android.network.client.HouseClient
import com.example.lifeshare_android.widget.SingleLiveEvent

class ShowHouseViewModel(application: Application) : BaseViewModel<House>(application) {

    private val houseClient = HouseClient()

    val houseId = SingleLiveEvent<Int>()

    val housePlace = MutableLiveData<Int>()
    val genderLimit = MutableLiveData<String>()


    fun setUp() {
        addDisposable(houseClient.getHouse(houseId.value!!), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: House) {

    }

    override fun onRetrieveBaseSuccess(message: String) {}
}