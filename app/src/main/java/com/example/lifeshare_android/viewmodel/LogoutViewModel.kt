package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.widget.SingleLiveEvent

class LogoutViewModel(application: Application) : BaseViewModel<Any>(application) {

    val backEvent = SingleLiveEvent<Unit>()
    val openLogin = SingleLiveEvent<Unit>()

    fun onClickLogout() {
        token = ""
        userId = ""
        openLogin.call()
    }

    fun onClickBack() {
        backEvent.call()
    }
}