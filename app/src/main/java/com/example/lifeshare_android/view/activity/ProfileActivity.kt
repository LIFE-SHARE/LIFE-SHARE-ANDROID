package com.example.lifeshare_android.view.activity

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityProfileBinding
import com.example.lifeshare_android.viewmodel.ProfileViewModel

class ProfileActivity : BaseActivity<ActivityProfileBinding, ProfileViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_profile
    }

    override fun getViewModel(): Class<ProfileViewModel> {
        return ProfileViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }
}
