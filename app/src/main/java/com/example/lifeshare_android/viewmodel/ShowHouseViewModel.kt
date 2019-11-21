package com.example.lifeshare_android.viewmodel

import android.app.Application

import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.HouseClient
import com.example.lifeshare_android.network.response.data.HouseData
import com.example.lifeshare_android.util.Strings
import com.example.lifeshare_android.widget.SingleLiveEvent

class ShowHouseViewModel(application: Application) : BaseViewModel<HouseData>(application) {

    private val houseClient = HouseClient()

    val backEvent = SingleLiveEvent<Unit>()

    val houseId = SingleLiveEvent<Int>()

    val name = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val genderLimit = MutableLiveData<Int>()
    val ageLimit = MutableLiveData<Int>()
    val contractperiod = MutableLiveData<Int>()
    val information = MutableLiveData<String>()
    val maxMember = MutableLiveData<Int>()
    val image = MutableLiveData<String>()

    fun onClickBackBtn() {
        backEvent.call()
    }

    fun setUp() {
        addDisposable(houseClient.getHouse(houseId.value!!), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: HouseData) {
        name.value = data.house_data.name
        address.value = data.house_data.address
        genderLimit.value = data.house_data.genderLimit
        ageLimit.value = data.house_data.ageLimit
        contractperiod.value = data.house_data.contractperiod
        information.value = data.house_data.information
        maxMember.value = data.house_data.
        image.value = Strings.MAIN_HOST + "/" + data.house_data.imageData
    }

    override fun onRetrieveBaseSuccess(message: String) {}
}