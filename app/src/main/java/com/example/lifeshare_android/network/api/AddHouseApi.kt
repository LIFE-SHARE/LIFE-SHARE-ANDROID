package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.network.request.AddRoomRequest

import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AddHouseApi {

    @Multipart
    @POST("/house/room")
    fun postRoom(@Header("x-access-token") token: String?,
                 @Part("houseId") houseId: RequestBody?,
                 @Part("people_count") peopleCnt: RequestBody?,
                 @Part("money") money: RequestBody?,
                 @Part image: MultipartBody.Part?): Call<AddRoomRequest?>?
}