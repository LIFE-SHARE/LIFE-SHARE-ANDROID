package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.network.api.HouseApi
import com.example.lifeshare_android.network.response.data.HouseData
import com.example.lifeshare_android.network.response.data.HouseDatas

import io.reactivex.Single

import okhttp3.MultipartBody
import okhttp3.RequestBody

class HouseClient : BaseClient<HouseApi>() {

    fun getHouse(houseId: Int): Single<HouseData> {
        return api.getHouse(houseId).map(getResponseObjectsFunction())
    }

    fun getAllHouse(token: String): Single<HouseDatas> {
        return api.getAllHouse(token).map(getResponseObjectsFunction())
    }

    fun addPostHouse(token: String,
                     maxMember: RequestBody,
                     name: RequestBody,
                     address: RequestBody,
                     genderLimit: RequestBody,
                     ageLimit: RequestBody,
                     contractperiod: RequestBody,
                     information: RequestBody,
                     image: MultipartBody.Part): Single<Any> {
        return api.postHouse(token, maxMember, name, address, genderLimit, ageLimit, contractperiod, information, image).map(getResponseMessageFunction())
    }

    override fun type(): Class<HouseApi> {
        return HouseApi::class.java
    }
}