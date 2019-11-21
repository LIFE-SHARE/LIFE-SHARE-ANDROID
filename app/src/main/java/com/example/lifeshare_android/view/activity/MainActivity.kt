package com.example.lifeshare_android.view.activity

import android.content.Intent

import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityMainBinding
import com.example.lifeshare_android.viewmodel.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            addHouseEvent.observe(this@MainActivity, Observer {
                startActivityWithFinish(AddHouseActivity::class.java)
            })

            with(houseAdapter) {

                openHouse.observe(this@MainActivity, Observer {
                    startActivity(Intent(this@MainActivity, ShowHouseActivity::class.java)
                        .putExtra("homeId", it))
                    finish()
                })
            }
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllHouse()
    }
}
