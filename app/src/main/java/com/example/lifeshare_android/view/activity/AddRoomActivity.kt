package com.example.lifeshare_android.view.activity

import android.content.Intent

import android.os.Bundle

import androidx.lifecycle.Observer

import com.bumptech.glide.Glide

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BasePictureActivity
import com.example.lifeshare_android.databinding.ActivityAddRoomBinding
import com.example.lifeshare_android.viewmodel.AddRoomViewModel

import okhttp3.RequestBody

class AddRoomActivity : BasePictureActivity<ActivityAddRoomBinding, AddRoomViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_add_room
    }

    override fun getViewModel(): Class<AddRoomViewModel> {
        return AddRoomViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            backMessageToast.observe(this@AddRoomActivity, Observer {
                simpleToast("취소 되었습니다")
            })

            nullPointEvent.observe(this@AddRoomActivity, Observer {
                simpleToast("사진을 설정해주세요")
            })

            goToAlbum.observe(this@AddRoomActivity, Observer {
                tedPermission()
                goToAlbum()
            })

            goToCrop.observe(this@AddRoomActivity, Observer {
                goToCropPage(viewModel.tempPictureUri.value!!, viewModel.pictureUri.value!!)
            })

            addRoomEvent.observe(this@AddRoomActivity, Observer {
                request.peopleCnt = binding.roomMaxMemberText.text.toString().toInt()
                request.money = binding.roomMoneyText.text.toString()
                addPostRoom()
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUp()
    }

    private fun setUp() {
        when {
            intent!!.hasExtra("houseId") -> viewModel.houseId.value = intent!!.getSerializableExtra("houseId")!! as RequestBody
            else -> {
                simpleToast("houseId : NullPointException")
            }
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
        Glide.with(this).load(viewModel.pictureUri.value).into(binding.roomImage)
    }

    private fun isEmpty(): Boolean {
        return viewModel.request.houseId!!.toString().isEmpty() || viewModel.request.peopleCnt!!.toString().isEmpty() ||
                viewModel.request.money!!.isEmpty() || viewModel.request.image!!.isEmpty()
    }
}