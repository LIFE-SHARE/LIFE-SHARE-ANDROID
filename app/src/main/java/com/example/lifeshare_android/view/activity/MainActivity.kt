package com.example.lifeshare_android.view.activity

import android.app.AlertDialog

import android.content.Intent

import android.os.Bundle

import android.widget.Toast

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
                startActivity(AddHouseActivity::class.java)
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
        viewModel.getAllHouse()
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
        super.onBackPressed()

        val builder = AlertDialog.Builder(this)

        builder.setTitle("종료").setMessage("종료 하시겠습니까 ?")

        builder.setPositiveButton("확인") { dialog, id ->
            Toast.makeText(applicationContext, "OK Click", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("취소") { dialog, id ->
            Toast.makeText(applicationContext, "Cancel Click", Toast.LENGTH_SHORT).show()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
