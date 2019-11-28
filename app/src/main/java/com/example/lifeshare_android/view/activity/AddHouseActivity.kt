package com.example.lifeshare_android.view.activity

import android.content.Intent

import androidx.lifecycle.Observer

import com.bumptech.glide.Glide

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BasePictureActivity
import com.example.lifeshare_android.databinding.ActivityAddHouseBinding
import com.example.lifeshare_android.viewmodel.AddHouseViewModel

class AddHouseActivity : BasePictureActivity<ActivityAddHouseBinding, AddHouseViewModel>() {

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

            backMessageToast.observe(this@AddHouseActivity, Observer {
                simpleToast("취소 되었습니다")
            })

            nullPointImageEvent.observe(this@AddHouseActivity, Observer {
                simpleToast(it!!)
            })

            goToAlbum.observe(this@AddHouseActivity, Observer {
                tedPermission()
                goToAlbum()
            })

            goToCrop.observe(this@AddHouseActivity, Observer {
                goToCropPage(viewModel.tempPictureUri.value!!, viewModel.pictureUri.value!!)
            })

            addHouseEvent.observe(this@AddHouseActivity, Observer {
                request.name = binding.houseNameText.text.toString()
                request.address = binding.houseAddressText.text.toString()
                request.genderLimit = binding.houseGenderLimitText.text.toString()
                request.ageLimit = binding.houseAgeLimitText.text.toString().toInt()
                request.maxMember = binding.houseMaxMemberText.text.toString().toInt()
                request.contractperiod = binding.houseContractperiodText.text.toString()
                request.information = binding.houseInformationText.text.toString()
                addPostHouse()
            })

            onSuccessEvent.observe(this@AddHouseActivity, Observer {
                simpleToast(it!!)
                startActivityWithFinish(MainActivity::class.java)
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