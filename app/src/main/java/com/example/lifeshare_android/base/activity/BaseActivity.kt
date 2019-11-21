package com.example.lifeshare_android.base.activity

import android.app.ActivityOptions

import android.content.Intent

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.Bundle

import android.view.animation.AlphaAnimation
import android.view.animation.Animation

import android.widget.Toast

import androidx.annotation.LayoutRes

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProviders

import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.viewmodel.BaseViewModel

abstract class BaseActivity<VB : ViewDataBinding, VM : BaseViewModel<*>> : AppCompatActivity() {

    var intentActivityAnim = ActivityOptions.makeCustomAnimation(applicationContext, R.anim.from_right, R.anim.to_left)

    protected val anim: Animation = AlphaAnimation(0.0f, 1.0f)

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getViewModel(): Class<VM>

    protected abstract fun getBindingVariable(): Int

    protected abstract fun initObserver()

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performDataBinding()
        initObserver()
    }

    private fun performDataBinding() {
        performViewDataBinding()
    }

    private fun performViewDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        this.viewModel = if(::viewModel.isInitialized) viewModel else ViewModelProviders.of(this).get(getViewModel())
        binding.lifecycleOwner = this
        binding.setVariable(getBindingVariable(), viewModel)
        binding.executePendingBindings()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::binding.isInitialized) binding.unbind()
    }

    override fun setRequestedOrientation(requestedOrientation: Int) {
        if (VERSION.SDK_INT != VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation)
        }
    }

    protected fun startActivity(activity: Class<*>) {
        startActivity(Intent(this, activity))
    }

    protected fun startActivityWithFinish(activity: Class<*>) {
        startActivity(Intent(this, activity))
        finish()
    }

    protected fun simpleToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun simpleToast(message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun <T : Fragment?> newInstance(fragment: T): T {
            val args = Bundle()
            fragment!!.arguments = args
            return fragment
        }
    }

    protected fun initSetBlinkAnimation() {
        anim.duration = 50 // You can manage the time of the blink with this parameter
        anim.startOffset = 20
        anim.repeatMode = Animation.REVERSE
        anim.repeatCount = 5 // blink time
    }
}