package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel

class AddRoomViewModel(application: Application) : BaseViewModel<Any>(application) {

    override fun onRetrieveDataSuccess(data: Any) {}

    override fun onRetrieveBaseSuccess(message: String) {}
}