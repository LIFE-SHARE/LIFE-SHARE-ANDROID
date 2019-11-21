package com.example.lifeshare_android.view.activity

import android.content.Intent

import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityGuestMainBinding
import com.example.lifeshare_android.viewmodel.GuestMainViewModel

class GuestMainActivity : BaseActivity<ActivityGuestMainBinding, GuestMainViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_guest_main
    }

    override fun getViewModel(): Class<GuestMainViewModel> {
        return GuestMainViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            notificationEvent.observe(this@GuestMainActivity, Observer {

            })

            searchEvent.observe(this@GuestMainActivity, Observer {
                startActivity(Intent(applicationContext, SearchActivity::class.java), activityOptions.toBundle())
            })
        }
    }
}