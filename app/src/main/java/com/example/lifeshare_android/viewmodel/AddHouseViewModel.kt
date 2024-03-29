package com.example.lifeshare_android.viewmodel

import android.app.Application

import android.content.Intent

import android.net.Uri

import android.os.Environment

import androidx.lifecycle.MutableLiveData

import com.example.lifeshare_android.base.viewmodel.BaseViewModel
import com.example.lifeshare_android.network.client.HouseClient
import com.example.lifeshare_android.network.request.AddHouseRequest
import com.example.lifeshare_android.widget.SingleLiveEvent

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

import java.io.File
import java.io.IOException

import java.lang.NullPointerException

import java.util.*

class AddHouseViewModel(application: Application) : BaseViewModel<Any>(application) {

    private val houseClient = HouseClient()

    val request = AddHouseRequest()

    val addHouseEvent = SingleLiveEvent<Unit>()
    val goToAlbum = SingleLiveEvent<Unit>()
    val goToCrop = SingleLiveEvent<Unit>()
    val backMessageToast = SingleLiveEvent<Unit>()
    val nullPointImageEvent = SingleLiveEvent<String>()
    val onSuccessEvent = SingleLiveEvent<String>()

    val tempPictureUri = MutableLiveData<Uri>()
    val pictureUri = MutableLiveData<Uri>()
    private val imageFile = MutableLiveData<File>()
    private val image = MutableLiveData<MultipartBody.Part>()
    private val name = MutableLiveData<RequestBody>()
    private val address = MutableLiveData<RequestBody>()
    private val genderLimit = MutableLiveData<RequestBody>()
    private val ageLimit = MutableLiveData<RequestBody>()
    private val contractperiod = MutableLiveData<RequestBody>()
    private val maxMember = MutableLiveData<RequestBody>()
    private val information = MutableLiveData<RequestBody>()

    fun addPostHouse() {
        when {
            !setRequest() -> return
            else -> {
                addDisposable(houseClient.addPostHouse(token, name.value!!, address.value!!, genderLimit.value!!,
                    ageLimit.value!!, maxMember.value!!, contractperiod.value!!, information.value!!, image.value!!), baseObserver)
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
        val file = File(Environment.getExternalStorageDirectory().toString() + "/LifeShareHouse")
        when {
            !file.exists() -> file.mkdirs()
        }
        imageFile.value = File(Environment.getExternalStorageDirectory().toString() + "/LifeShareHouse/"
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
            name.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.name!!)
            address.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.address!!)
            genderLimit.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.genderLimit!!)
            ageLimit.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.ageLimit!!.toString())
            maxMember.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.maxMember!!.toString())
            contractperiod.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.contractperiod!!)
            information.value = RequestBody.create("text/plain".toMediaTypeOrNull(), request.information!!)
        }
        catch (e: NullPointerException) {
            nullPointImageEvent.value = "사진을 설정해주세요"
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

    fun onClickAddHouseBtn() {
        addHouseEvent.call()
    }

    override fun onRetrieveBaseSuccess(message: String) {
        super.onRetrieveBaseSuccess(message)

        onSuccessEvent.value = message
    }
}