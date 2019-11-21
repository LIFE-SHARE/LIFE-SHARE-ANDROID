package com.example.lifeshare_android.view.activity

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityAddRoomBinding
import com.example.lifeshare_android.viewmodel.AddRoomViewModel

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>() {

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

        }
    }
}