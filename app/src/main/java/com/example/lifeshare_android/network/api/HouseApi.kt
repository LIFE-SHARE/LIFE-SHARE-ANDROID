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
    fun addPostHouse(@Header("x-access-token") token: String,
                     @Part("name") name: RequestBody,
                     @Part("address") address: RequestBody,
                     @Part("genderLimit") genderLimit: RequestBody,
                     @Part("ageLimit") ageLimit: RequestBody,
                     @Part("maxMember") maxMember: RequestBody,
                     @Part("contractperiod") contractperiod: RequestBody,
                     @Part("information") information: RequestBody,
                     @Part image: MultipartBody.Part): Single<retrofit2.Response<Response<Any>>>

    @Multipart
    @POST("/house/room")
    fun addPostRoom(@Header("x-access-token") token: String,
                    @Part("houseId") houseId: Int,
                    @Part("people_count") peopleCnt: RequestBody,
                    @Part("money") money: RequestBody,
                    @Part image: MultipartBody.Part): Single<retrofit2.Response<Response<Any>>>
}