package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.HouseClient
import com.example.lifeshare_android.network.response.data.HouseDatas
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.adapter.HouseAdapter

class MainViewModel(application: Application) : BaseViewModel<HouseDatas>(application) {

    private val adminClient = HouseClient()

    val houseAdapter = HouseAdapter()

    val addHouseEvent = SingleLiveEvent<Unit>()

    val userName = SingleLiveEvent<String>()

    fun onClickAddHouse() {
        addHouseEvent.call()
    }

    fun getAllHouse() {
        addDisposable(adminClient.getAllHouse(token), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: HouseDatas) {
        houseAdapter.updateList(data.house_data)
    }

    override fun onRetrieveBaseSuccess(message: String) {}
}