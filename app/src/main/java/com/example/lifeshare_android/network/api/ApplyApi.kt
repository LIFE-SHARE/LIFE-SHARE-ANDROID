package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.network.response.Response
import com.example.lifeshare_android.network.response.data.ApplyDatas

import io.reactivex.Single

import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApplyApi {

    @GET("/house/apply")
    fun getApplyAll(@Header("x-access-token") token: String,
                    @Query("houseId") houseId: Int): Single<retrofit2.Response<Response<ApplyDatas>>>

    @GET("/house/apply/accept")
    fun accept(@Header("x-access-token") token: String,
               @Query("applyId") applyId: Int): Single<retrofit2.Response<Response<Any>>>

    @DELETE("/house/apply")
    fun refusal(@Header("x-access-token") token: String,
                @Query("applyId") applyId: Int): Single<retrofit2.Response<Response<Any>>>
}