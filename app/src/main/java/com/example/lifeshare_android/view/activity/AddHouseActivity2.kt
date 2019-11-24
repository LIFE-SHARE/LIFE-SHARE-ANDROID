package com.example.lifeshare_android.view.activity

import android.content.Intent
import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.base.activity.BasePictureActivity
import com.example.lifeshare_android.databinding.ActivityAddHouseBinding
import com.example.lifeshare_android.viewmodel.AddHouseViewModel

class AddHouseActivity2 : BasePictureActivity<ActivityAddHouseBinding, AddHouseViewModel>() {

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
                goToAlbum()
            })
        }
    }

    override fun requestNotOkEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pickNextEvent(data: Intent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cropNextEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}