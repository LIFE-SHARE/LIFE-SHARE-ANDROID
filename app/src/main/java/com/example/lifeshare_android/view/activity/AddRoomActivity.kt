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
import com.example.lifeshare_android.widget.extension.shortToast

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
                this@AddRoomActivity.shortToast("취소 되었습니다")
            })

            nullPointImageEvent.observe(this@AddRoomActivity, Observer {
                this@AddRoomActivity.shortToast(it!!)
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

            onSuccessEvent.observe(this@AddRoomActivity, Observer {
                this@AddRoomActivity.shortToast(it!!)
                startActivity(Intent(this@AddRoomActivity, ShowHouseActivity::class.java)
                    .putExtra("houseId", houseId.value!!))
                finish()
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUp()
    }

    private fun setUp() {
        when {
            intent!!.hasExtra("houseId") -> viewModel.houseId.value = intent!!.getStringExtra("houseId")!!.toInt()
            else -> {
                this@AddRoomActivity.shortToast("houseId : NullPointException")
            }
        }
        binding.roomImage.setImageResource(R.drawable.none_image)
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
}