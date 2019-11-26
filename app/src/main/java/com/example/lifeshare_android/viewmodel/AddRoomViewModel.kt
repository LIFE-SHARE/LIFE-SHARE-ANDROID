package com.example.lifeshare_android.viewmodel

import android.app.Application

import android.content.Intent

import android.net.Uri

import android.os.Environment

import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.HouseClient
import com.example.lifeshare_android.network.request.AddRoomRequest
import com.example.lifeshare_android.widget.SingleLiveEvent

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

import java.io.File
import java.io.IOException

import java.lang.NullPointerException

import java.util.*

class AddRoomViewModel(application: Application) : BaseViewModel<Any>(application) {

    private val houseClient = HouseClient()

    val request = AddRoomRequest()

    val addRoomEvent = SingleLiveEvent<Unit>()
    val goToAlbum = SingleLiveEvent<Unit>()
    val goToCrop = SingleLiveEvent<Unit>()
    val backMessageToast = SingleLiveEvent<Unit>()
    val nullPointEvent = SingleLiveEvent<Unit>()

    val tempPictureUri = MutableLiveData<Uri>()
    val pictureUri = MutableLiveData<Uri>()
    private val imageFile = MutableLiveData<File>()
    private val image = MutableLiveData<MultipartBody.Part>()
    val houseId = MutableLiveData<RequestBody>()
    private val peopleCnt = MutableLiveData<RequestBody>()
    private val money = MutableLiveData<RequestBody>()

    fun addPostRoom() {
        when {
            !setRequest() -> return
            else -> {
                addDisposable(houseClient.addPostRoom(token, houseId.value!!, peopleCnt.value!!, money.value!!, image.value!!), baseObserver)
            }
        }
    }

    fun savePickData(data: Intent) {
        tempPictureUri.value = data.data
    }

    fun cropImage() {
        createFile()
        goToCrop.call()
    }

    private fun createFile() {
        val file = File(Environment.getExternalStorageDirectory().toString() + "/LifeShare/Room")
        if (!file.exists()) file.mkdirs()
        imageFile.value = File(
            Environment.getExternalStorageDirectory().toString() + "/LifeShare/Room"
                    + Random().nextInt(999999999).toString() + ".jpg")
        try {
            imageFile.value!!.createNewFile()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        pictureUri.value = Uri.fromFile(imageFile.value)
    }

    private fun setRequest(): Boolean {
        try {
            val requestFile: RequestBody = RequestBody.create("image/*".toMediaTypeOrNull(), imageFile.value!!)
            image.value = MultipartBody.Part.createFormData("image", imageFile.value!!.name, requestFile)
            houseId.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.houseId.toString())
            peopleCnt.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.peopleCnt.toString())
            money.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.money)
        }
        catch (e: NullPointerException) {
            nullPointEvent.call()
            return false
        }
        return true
    }

    fun deleteFile() {
        tempPictureUri.value = null
        imageFile.value = null
        pictureUri.value = null
        backMessageToast.call()
    }

    fun onClickInputImageBtn() {
        goToAlbum.call()
    }

    fun onClickAddRoomBtn() {
        addRoomEvent.call()
    }
}