package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.ApplyClient
import com.example.lifeshare_android.network.response.data.ApplyDatas
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.adapter.ApplyAdapter

class ShowApplyViewModel(application: Application) : BaseViewModel<ApplyDatas>(application) {

    private val applyClient = ApplyClient()

    val applyAdapter = ApplyAdapter()

    val houseId = SingleLiveEvent<Int>()

    fun setUp() {
        addDisposable(applyClient.getApplyAll(token, houseId.value!!), dataObserver)
    }

    fun accept(applyId: Int) {
        addDisposable(applyClient.accept(token, applyId), baseObserver)
    }

    fun refusal(applyId: Int) {
        addDisposable(applyClient.refusal(token, applyId), baseObserver)
    }

    override fun onRetrieveDataSuccess(data: ApplyDatas) {
        applyAdapter.updateList(data.applyList)
    }

    override fun onRetrieveBaseSuccess(message: String) {}
}