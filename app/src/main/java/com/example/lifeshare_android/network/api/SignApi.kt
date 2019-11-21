package com.example.lifeshare_android.network.api

import com.example.lifeshare_android.model.user.User

import com.example.lifeshare_android.network.request.LoginRequest
import com.example.lifeshare_android.network.response.Response
import com.example.lifeshare_android.network.response.data.LoginData

import io.reactivex.Single

import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SignApi {

    @POST("/auth/login")
    fun login(@Body loginRequest: LoginRequest): Single<retrofit2.Response<Response<LoginData>>>

    @POST("/auth/autoLogin")
    fun autoLogin(@Header("Authorization") token: String): Single<retrofit2.Response<Response<User>>>
}