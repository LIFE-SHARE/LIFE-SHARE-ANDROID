package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.network.response.Response
import com.example.lifeshare_android.network.response.data.HouseData
import com.example.lifeshare_android.network.response.data.HouseDatas

import io.reactivex.Single

import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.http.*

interface HouseApi {

    @GET("/house")
    fun getHouse(@Query("houseId") houseId: Int): Single<retrofit2.Response<Response<HouseData>>>

    @GET("/house/my")
    fun getAllHouse(@Header("x-access-token") token: String): Single<retrofit2.Response<Response<HouseDatas>>>

    @Multipart
    @POST("/house")
    fun postHouse(@Header("x-access-token") token: String,
                  @retrofit2.http.Part("maxMember") maxMember: RequestBody,
                  @retrofit2.http.Part("name") name: RequestBody,
                  @retrofit2.http.Part("address") address: RequestBody,
                  @retrofit2.http.Part("genderLimit") genderLimit: RequestBody,
                  @retrofit2.http.Part("ageLimit") ageLimit: RequestBody,
                  @retrofit2.http.Part("contractperiod") contractperiod: RequestBody,
                  @retrofit2.http.Part("information") information: RequestBody,
                  @retrofit2.http.Part image: MultipartBody.Part): Single<retrofit2.Response<Response<Any>>>
}