package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.adapter.GuestMainAdapter

class GuestMainViewModel(application: Application) : BaseViewModel<Any>(application) {



    val guestMainAdapter = GuestMainAdapter()

    val searchEvent = SingleLiveEvent<Unit>()
    val notificationEvent = SingleLiveEvent<Unit>()

    fun onClickSearchBtn() {
        searchEvent.call()
    }

    fun onClickNotificationBtn() {
        notificationEvent.call()
    }

    override fun onRetrieveDataSuccess(data: Any) {}

    override fun onRetrieveBaseSuccess(message: String) {}
}