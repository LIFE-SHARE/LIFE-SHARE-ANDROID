package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.network.response.Response
import com.example.lifeshare_android.network.response.data.ApplyDatas
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApplyApi {

    @GET("/house/apply")
    fun getApplyAll(@Query("houseId") houseId: Int): Single<retrofit2.Response<Response<ApplyDatas>>>
}