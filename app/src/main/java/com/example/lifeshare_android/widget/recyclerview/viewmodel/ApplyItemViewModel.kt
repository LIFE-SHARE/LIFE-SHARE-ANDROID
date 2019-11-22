package com.example.lifeshare_android.widget.recyclerview.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.lifeshare_android.base.viewmodel.BaseItemViewModel
import com.example.lifeshare_android.model.apply.Apply
import com.example.lifeshare_android.widget.recyclerview.navigator.ApplyItemNavigator

class ApplyItemViewModel : BaseItemViewModel<Apply, ApplyItemNavigator>() {

    private val genderText = MutableLiveData<String>()

    val firstInfoText = MutableLiveData<String>()
    val nameText = MutableLiveData<String>()
    val messageText = MutableLiveData<String>()

    fun onClickAccept() {
        getNavigator().onClickAcceptBtn()
    }

    fun onClickRefusal() {
        getNavigator().onClickRefusalBtn()
    }

    override fun bind(data: Apply) {
        if(data.gender == 0) {
            genderText.value = "남성"
        }
        else if(data.gender == 1) {
            genderText.value = "여성"
        }
        firstInfoText.value = String.format(data.join_date.split("T")[0] + "에 " + genderText.value + " " + data.userAge + "세")
        nameText.value = String.format(data.userName)
        messageText.value = String.format(data.message)
    }
}