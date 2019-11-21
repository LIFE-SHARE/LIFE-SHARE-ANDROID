package com.example.lifeshare_android.base.activity

import android.Manifest

import android.app.Activity

import android.content.Intent

import android.net.Uri

import android.provider.MediaStore

import android.widget.Toast

import androidx.databinding.ViewDataBinding

import com.example.lifeshare_android.base.viewmodel.BaseViewModel

import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

import java.util.ArrayList

abstract class BasePictureActivity<VB : ViewDataBinding, VM : BaseViewModel<*>> : BaseActivity<VB, VM>() {

    private val PICK_FROM_ALBUM = 1
    private val REQUEST_IMAGE_CROP = 2

    protected fun tedPermission() {
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {}
            override fun onPermissionDenied(deniedPermissions: ArrayList<String?>?) {
                Toast.makeText(applicationContext, "접근을 허용해야 사진을 등록할 수 있습니다", Toast.LENGTH_LONG).show()
            }
        }
        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check()
    }

    protected fun goToAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            requestNotOkEvent()
        }
        when (requestCode) {
            PICK_FROM_ALBUM -> {
                if (data == null) {
                    return
                }
                pickNextEvent(data)
            }
            REQUEST_IMAGE_CROP -> {
                cropNextEvent()
            }
        }
    }

    protected abstract fun requestNotOkEvent()

    protected abstract fun pickNextEvent(data: Intent)

    protected abstract fun cropNextEvent()
}