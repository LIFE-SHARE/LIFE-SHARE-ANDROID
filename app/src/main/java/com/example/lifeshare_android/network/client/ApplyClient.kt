package com.example.lifeshare_android.network.client

import com.example.lifeshare_android.base.BaseClient
import com.example.lifeshare_android.network.api.ApplyApi
import com.example.lifeshare_android.network.response.data.ApplyDatas
import io.reactivex.Single

class ApplyClient : BaseClient<ApplyApi>() {

    fun getApplyAll(houseId: Int): Single<ApplyDatas> {
        return api.getApplyAll(houseId).map(getResponseObjectsFunction())
    }

    override fun type(): Class<ApplyApi> {
        return ApplyApi::class.java
    }
}