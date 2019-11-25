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

            nullPointEvent.observe(this@AddHouseActivity, Observer {
                simpleToast("사진을 설정해주세요")
            })

            goToAlbum.observe(this@AddHouseActivity, Observer {
                tedPermission()
                goToAlbum()
            })

            goToCrop.observe(this@AddHouseActivity, Observer {
                goToCropPage(viewModel.tempPictureUri.value!!, viewModel.pictureUri.value!!)
            })

            addHouseEvent.observe(this@AddHouseActivity, Observer {
                when {
                    isEmpty() -> {
                        simpleToast("빈칸 없이 입력해주세요")
                        return@Observer
                    }
                    else -> {
                        addPostHouse()
                    }
                }
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

    private fun isEmpty(): Boolean {
        return viewModel.request.name.isEmpty() || viewModel.request.address.isEmpty() ||
                viewModel.request.genderLimit.isEmpty() || viewModel.request.ageLimit.toString().isEmpty() ||
                viewModel.request.contractperiod.isEmpty() || viewModel.request.maxMember.toString().isEmpty() ||
                viewModel.request.information.isEmpty() || viewModel.request.image.isEmpty()
    }
}