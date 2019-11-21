package com.example.lifeshare_android.viewmodel

import android.app.Application

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.widget.recyclerview.adapter.AdminMainAdapter

class AdminViewModel(application: Application) : BaseViewModel<Any>(application) {

    val adminMainAdapter = AdminMainAdapter()

    override fun onRetrieveDataSuccess(data: Any) {}

    override fun onRetrieveBaseSuccess(message: String) {}
}