package com.example.lifeshare_android.viewmodel

import android.app.Application

import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.HouseClient
import com.example.lifeshare_android.network.response.data.HouseData
import com.example.lifeshare_android.util.Constants
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.adapter.RoomAdapter

class ShowHouseViewModel(application: Application) : BaseViewModel<HouseData>(application) {

    private val houseClient = HouseClient()

    val roomAdapter = RoomAdapter()

    val backEvent = SingleLiveEvent<Unit>()
    val addRoomEvent = SingleLiveEvent<Int>()
    val applyCheckEvent = SingleLiveEvent<Int>()

    val houseId = SingleLiveEvent<Int>()

    val name = MutableLiveData<String>()
    val address = MutableLiveData<String>()
    val genderLimit = MutableLiveData<String>()  // TODO : 0 남여 혼용, 1 남여분리, 2 남성 전용, 3 여성전용
    val ageLimit = MutableLiveData<String>()
    val contractperiod = MutableLiveData<String>()
    val information = MutableLiveData<String>()
    val maxMember = MutableLiveData<String>()
    val image = MutableLiveData<String>()

    fun onClickBackBtn() {
        backEvent.call()
    }

    fun onClickAddRoomBtn() {
        addRoomEvent.value = houseId.value!!
    }

    fun onClickApplyBtn() {
        applyCheckEvent.value = houseId.value!!
    }

    fun setUp() {
        addDisposable(houseClient.getHouse(houseId.value!!), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: HouseData) {
        name.value = data.house_data.name
        address.value = data.house_data.address
        when (data.house_data.genderLimit) {
            "남여혼용" -> {
                genderLimit.value = "남여혼용"
            }
            "남여분리" -> {
                genderLimit.value = "남여분리"
            }
            "남성전용" -> {
                genderLimit.value = "남성전용"
            }
            "여성전용" -> {
                genderLimit.value = "여성전용"
            }
        }
        ageLimit.value = String.format("만 " + data.house_data.ageLimit + "세 이상")
        contractperiod.value = data.house_data.contractperiod
        information.value = data.house_data.information
        maxMember.value = String.format("" + data.house_data.maxMember + "명")
        image.value = Constants.MAIN_HOST + "/" + data.house_data.imageData

        roomAdapter.updateList(data.room_data)
    }
}