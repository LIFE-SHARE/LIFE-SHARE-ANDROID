package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.SignClient
import com.example.lifeshare_android.network.request.LoginRequest
import com.example.lifeshare_android.network.response.data.LoginData
import com.example.lifeshare_android.widget.SingleLiveEvent

class LoginViewModel(application: Application) : BaseViewModel<LoginData>(application) {

    private val signClient = SignClient()

    var request = LoginRequest()

    val loginEvent = SingleLiveEvent<Unit>()
    val onSuccessEvent = SingleLiveEvent<String>()

    fun login() {
        addDisposable(signClient.login(request), dataObserver)
    }

    fun onClickLogin() {
        loginEvent.call()
    }

    override fun onRetrieveDataSuccess(data: LoginData) {
        onSuccessEvent.value = data.member.name
    }

    override fun onRetrieveBaseSuccess(message: String) {}
}