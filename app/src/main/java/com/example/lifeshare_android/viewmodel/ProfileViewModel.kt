package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.widget.SingleLiveEvent

class ProfileViewModel(application: Application) : BaseViewModel<Any>(application) {

    val backEvent = SingleLiveEvent<Unit>()

    fun onClickBackBtn() {
        backEvent.call()
    }

    override fun onRetrieveDataSuccess(data: Any) {}

    override fun onRetrieveBaseSuccess(message: String) {}
}