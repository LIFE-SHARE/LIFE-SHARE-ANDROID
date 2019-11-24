package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.widget.SingleLiveEvent

class AddHouseViewModel(application: Application) : BaseViewModel<Any>(application) {

    val addImageEvent = SingleLiveEvent<Unit>()

    fun addInputImageBtn() {
        addImageEvent.call()
    }

    override fun onRetrieveDataSuccess(data: Any) {}

    override fun onRetrieveBaseSuccess(message: String) {}
}