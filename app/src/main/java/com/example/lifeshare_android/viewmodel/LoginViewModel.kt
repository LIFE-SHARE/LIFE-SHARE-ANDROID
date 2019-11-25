package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.model.user.Member
import com.example.lifeshare_android.network.client.SignClient
import com.example.lifeshare_android.network.request.LoginRequest
import com.example.lifeshare_android.network.response.data.LoginData
import com.example.lifeshare_android.widget.SingleLiveEvent

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application) : BaseViewModel<LoginData>(application) {

    private val signClient = SignClient()

    var request = LoginRequest()

    val loginEvent = SingleLiveEvent<Unit>()
    val onSuccessEvent = SingleLiveEvent<String>()

    fun login() {
        addDisposable(signClient.login(request), dataObserver)
    }

    private fun insertLoginData(loginData: LoginData) {
        insertToken(loginData.token)
        insertId(loginData.member.id)
        insertUser(loginData.member)
    }

    private fun insertToken(token: String) {
        this.token = token
    }

    private fun insertId(id: String) {
        userId = id
    }

    private fun insertUser(member: Member) {
        CompositeDisposable().add(repository.insertUser(member)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {},
                { error -> onErrorEvent.value = error }))
    }

    fun onClickLogin() {
        loginEvent.call()
    }

    override fun onRetrieveDataSuccess(data: LoginData) {
        insertLoginData(data)
        onSuccessEvent.value = data.member.name
    }
}