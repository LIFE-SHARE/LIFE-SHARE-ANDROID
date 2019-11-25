package com.example.lifeshare_android.viewmodel

import android.app.Application

import android.content.Intent

import android.net.Uri

import android.os.Environment

import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.HouseClient
import com.example.lifeshare_android.widget.SingleLiveEvent

import okhttp3.MultipartBody

import java.io.File
import java.io.IOException

import java.util.*

class AddHouseViewModel(application: Application) : BaseViewModel<Any>(application) {

    private val houseClient = HouseClient()

    val addHouseEvent = SingleLiveEvent<Unit>()
    val goToAlbum = SingleLiveEvent<Unit>()
    val goToCrop = SingleLiveEvent<Unit>()
    val backMessageToast = SingleLiveEvent<Unit>()

    val tempPictureUri = MutableLiveData<Uri>()
    val pictureUri = MutableLiveData<Uri>()
    private val pictureFile = MutableLiveData<File>()
    private val picture = MutableLiveData<MultipartBody.Part>()

    fun addPostHouse() {
//        addDisposable(houseClient.addPostHouse(token), baseObserver)
    }

    fun addInputImageBtn() {
        goToAlbum.call()
    }

    fun addHouseBtn() {
        addHouseEvent.call()
    }

    fun savePickData(data: Intent) {
        tempPictureUri.value = data.data
    }

    fun cropImage() {
        createFile()
        goToCrop.call()
    }

    private fun createFile() {
        val file = File(Environment.getExternalStorageDirectory().toString() + "/LifeShare/House")
        if (!file.exists()) file.mkdirs()
        pictureFile.value = File(Environment.getExternalStorageDirectory().toString() + "/LifeShare/House"
                + Random().nextInt(999999999).toString() + ".jpg")
        try {
            pictureFile.value!!.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        pictureUri.value = Uri.fromFile(pictureFile.value)
    }

    fun deleteFile() {
        tempPictureUri.value = null
        pictureFile.value = null
        pictureUri.value = null
        backMessageToast.call()
    }

    override fun onRetrieveDataSuccess(data: Any) {}

    override fun onRetrieveBaseSuccess(message: String) {}
}