package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.model.house.House
import com.example.lifeshare_android.network.response.Response
import com.example.lifeshare_android.network.response.data.HouseData
import com.example.lifeshare_android.network.response.data.HouseDatas

import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface HouseApi {

    @GET("/house")
    fun getHouse(@Query("houseId") houseId: Int): Single<retrofit2.Response<Response<HouseData>>>

    @GET("/house/my")
    fun getAllHouse(@Header("x-access-token") token: String): Single<retrofit2.Response<Response<HouseDatas>>>
}