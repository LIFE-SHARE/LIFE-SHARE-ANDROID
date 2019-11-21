package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.model.user.User
import com.example.lifeshare_android.network.api.SignApi
import com.example.lifeshare_android.network.request.LoginRequest
import com.example.lifeshare_android.network.response.data.LoginData

import io.reactivex.Single

class SignClient : BaseClient<SignApi>() {

    fun login(loginRequest: LoginRequest): Single<LoginData> {
        return api.login(loginRequest).map(getResponseObjectsFunction())
    }

//    fun signUp(signUpRequest: SignUpRequest): Single<String> {
//        return api.signUp(signUpRequest).map(getResponseMessageFunction())
//    }

    fun autoLogin(token: String): Single<User> {
        return api.autoLogin(token).map(getResponseObjectsFunction())
    }

    override fun type(): Class<SignApi> {
        return SignApi::class.java
    }
}