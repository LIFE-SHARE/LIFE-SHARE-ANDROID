package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.network.api.ApplyApi
import com.example.lifeshare_android.network.response.data.ApplyDatas
import io.reactivex.Single

class ApplyClient : BaseClient<ApplyApi>() {

    fun getApplyAll(token: String, houseId: Int): Single<ApplyDatas> {
        return api.getApplyAll(token, houseId).map(getResponseObjectsFunction())
    }

    fun accept(token: String, applyId: Int): Single<Any> {
        return api.accept(token, applyId).map(getResponseMessageFunction())
    }

    fun refusal(token: String, applyId: Int): Single<Any> {
        return api.refusal(token, applyId).map(getResponseMessageFunction())
    }

    override fun type(): Class<ApplyApi> {
        return ApplyApi::class.java
    }
}