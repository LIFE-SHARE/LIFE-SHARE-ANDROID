package com.example.lifeshare_android.view.activity

import android.content.Intent

import android.os.Bundle

import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivityMainBinding
import com.example.lifeshare_android.view.dialog.LogoutDialog
import com.example.lifeshare_android.viewmodel.MainViewModel
import com.example.lifeshare_android.widget.extension.startActivity

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
                this@MainActivity.startActivity(AddHouseActivity::class.java)
            })

            with(houseAdapter) {

                openHouse.observe(this@MainActivity, Observer {
                    startActivity(Intent(this@MainActivity, ShowHouseActivity::class.java)
                        .putExtra("houseId", it!!))
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUp()
    }

    private fun setUp() {
        when {
            intent!!.hasExtra("userName") -> viewModel.userName.value = intent!!.getStringExtra("userName")
            else -> {
                viewModel.userName.value = "사용자"
            }
        }
    }

    override fun onBackPressed() {
        newInstance(LogoutDialog()).show(supportFragmentManager)
    }
}
