package com.example.lifeshare_android.view.animation

import android.os.Bundle

import android.view.animation.Animation
import android.view.animation.AnimationUtils

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BaseActivity
import com.example.lifeshare_android.databinding.ActivitySplashBinding
import com.example.lifeshare_android.view.activity.LoginActivity
import com.example.lifeshare_android.viewmodel.SplashViewModel

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun getViewModel(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val hold = AnimationUtils.loadAnimation(applicationContext, R.anim.splash_hold)!!
        val scale = AnimationUtils.loadAnimation(applicationContext, R.anim.splash_scale)!!

        hold.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                binding.animationLogo!!.clearAnimation()
                binding.animationLogo!!.startAnimation(scale)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        scale.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {}

            override fun onAnimationEnd(animation: Animation) {
                binding.animationLogo!!.clearAnimation()
                startActivityWithFinish(LoginActivity::class.java)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        binding.animationLogo!!.startAnimation(hold)
    }
}