package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.model.admin.ApplyList
import com.example.lifeshare_android.network.client.AdminClient
import com.example.lifeshare_android.widget.SingleLiveEvent
import com.example.lifeshare_android.widget.recyclerview.adapter.AdminMainAdapter

class AdminViewModel(application: Application) : BaseViewModel<List<ApplyList>>(application) {

    private val adminClient = AdminClient()

    val adminMainAdapter = AdminMainAdapter()

    val profileEvent = SingleLiveEvent<Unit>()
    val searchEvent = SingleLiveEvent<Unit>()

    fun onClickProfileBtn() {
        profileEvent.call()
    }

    fun onClickSearchBtn() {
        searchEvent.call()
    }

    fun getApplyListAllOutSide() {
        addDisposable(adminClient.getApplyListAll(token), dataObserver)
    }

    override fun onRetrieveDataSuccess(data: List<ApplyList>) {
        adminMainAdapter.updateList(data)
    }

    override fun onRetrieveBaseSuccess(message: String) {}
}