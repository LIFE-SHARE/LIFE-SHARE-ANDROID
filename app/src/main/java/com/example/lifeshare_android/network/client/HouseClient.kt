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
                     name: RequestBody,
                     address: RequestBody,
                     genderLimit: RequestBody,
                     ageLimit: RequestBody,
                     maxMember: RequestBody,
                     contractperiod: RequestBody,
                     information: RequestBody,
                     image: MultipartBody.Part): Single<Any> {
        return api.addPostHouse(token, name, address, genderLimit, ageLimit, maxMember, contractperiod, information, image).map(getResponseMessageFunction())
    }

    fun addPostRoom(token: String,
                    houseId: Int,
                    peopleCnt: RequestBody,
                    money: RequestBody,
                    image: MultipartBody.Part): Single<Any> {
        return api.addPostRoom(token, houseId, peopleCnt, money, image).map(getResponseMessageFunction())
    }

    override fun type(): Class<HouseApi> {
        return HouseApi::class.java
    }
}