package com.example.lifeshare_android.view.activity

import android.content.Intent

import androidx.lifecycle.Observer
import com.bumptech.glide.Glide

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BasePictureActivity
import com.example.lifeshare_android.database.sharedpreference.Token
import com.example.lifeshare_android.databinding.ActivityAddHouseBinding
import com.example.lifeshare_android.network.api.AddHouseApi
import com.example.lifeshare_android.util.UtilsJava
import com.example.lifeshare_android.viewmodel.AddHouseViewModel

class AddHouseActivity2 : BasePictureActivity<ActivityAddHouseBinding, AddHouseViewModel>() {

    var addHouseApi = UtilsJava.RETROFIT.create(AddHouseApi::class.java)

    override fun getLayoutId(): Int {
        return R.layout.activity_add_house
    }

    override fun getViewModel(): Class<AddHouseViewModel> {
        return AddHouseViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            addImageEvent.observe(this@AddHouseActivity2, Observer {
                tedPermission()
                goToAlbum()
            })

            addHouseEvent.observe(this@AddHouseActivity2, Observer {
                addPostHouse()
            })

            goToCrop.observe(this@AddHouseActivity2, Observer {
                goToCropPage(viewModel.tempPictureUri.value, viewModel.pictureUri.value)
            })
        }
    }

    override fun requestNotOkEvent() {
        viewModel.deleteFile()
    }

    override fun pickNextEvent(data: Intent) {
        viewModel.savePickData(data)
        viewModel.cropImage()
    }

    override fun cropNextEvent() {
        Glide.with(this).load(viewModel.pictureUri.value).into(binding.houseImage)
    }
}