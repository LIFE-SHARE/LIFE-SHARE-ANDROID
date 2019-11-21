package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.model.user.Profile

import com.example.lifeshare_android.network.response.Response

import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface UserApi {

    @GET("/api/getProfile")
    fun getProfile(@Header("token") token: String,
                   @Path("userId") userId: String): Single<retrofit2.Response<Response<Profile>>>
}