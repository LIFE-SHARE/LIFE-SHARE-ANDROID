package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.model.house.House
import com.example.lifeshare_android.network.api.HouseApi
import com.example.lifeshare_android.network.response.data.HouseData

import io.reactivex.Single

class HouseClient : BaseClient<HouseApi>() {

    fun getHouse(houseId: Int): Single<House> {
        return api.getHouse(houseId).map(getResponseObjectsFunction())
    }

    fun getAllHouse(token: String): Single<HouseData> {
        return api.getAllHouse(token).map(getResponseObjectsFunction())
    }

    override fun type(): Class<HouseApi> {
        return HouseApi::class.java
    }
}