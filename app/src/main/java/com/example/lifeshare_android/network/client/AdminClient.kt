package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.model.house.House
import com.example.lifeshare_android.network.api.AdminApi

import io.reactivex.Single

class AdminClient : BaseClient<AdminApi>() {

    fun getHouseListAll(token: String): Single<List<House>> {
        return api.getHouseListAll(token).map(getResponseObjectsFunction())
    }

    override fun type(): Class<AdminApi> {
        return AdminApi::class.java
    }
}