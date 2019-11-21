package com.example.lifeshare_android.view.activity

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityAdminMainBinding
import com.example.lifeshare_android.viewmodel.AdminViewModel

class AdminMainActivity : BaseActivity<ActivityAdminMainBinding, AdminViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_admin_main
    }

    override fun getViewModel(): Class<AdminViewModel> {
        return AdminViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }
}
