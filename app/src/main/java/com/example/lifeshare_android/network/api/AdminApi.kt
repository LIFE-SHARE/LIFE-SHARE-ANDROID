package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.model.admin.ApplyList
import com.example.lifeshare_android.network.response.Response

import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Header

interface AdminApi {

    @GET("손님 신청 정보 리스트")
    fun getApplyListAll(@Header("token") token: String): Single<retrofit2.Response<Response<List<ApplyList>>>>
}