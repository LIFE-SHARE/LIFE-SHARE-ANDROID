package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.network.api.SignApi
import com.example.lifeshare_android.network.request.LoginRequest
import com.example.lifeshare_android.network.response.data.LoginData

import io.reactivex.Single

class SignClient : BaseClient<SignApi>() {

    fun login(loginRequest: LoginRequest): Single<LoginData> {
        return api.login(loginRequest).map(getResponseObjectsFunction())
    }

    override fun type(): Class<SignApi> {
        return SignApi::class.java
    }
}