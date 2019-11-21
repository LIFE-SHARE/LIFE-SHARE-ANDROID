package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.model.admin.ApplyList
import com.example.lifeshare_android.network.api.AdminApi

import io.reactivex.Single

class AdminClient : BaseClient<AdminApi>() {

    fun getApplyListAll(token: String): Single<List<ApplyList>> {
        return api.getApplyListAll(token).map(getResponseObjectsFunction())
    }

    override fun type(): Class<AdminApi> {
        return AdminApi::class.java
    }
}