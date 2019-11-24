package com.example.lifeshare_android.base.activity

import android.Manifest

import android.content.Intent

import android.database.Cursor

import android.graphics.Bitmap
import android.graphics.BitmapFactory

import android.net.Uri

import android.provider.MediaStore

import android.util.Log

import android.widget.Toast

import androidx.databinding.ViewDataBinding

import com.example.lifeshare_android.base.viewmodel.BaseViewModel

import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

import java.io.File

import java.util.*

abstract class BasePictureActivity<VB : ViewDataBinding, VM : BaseViewModel<*>> : BaseActivity<VB, VM>() {

    var tempFile: File? = null
    var file: File? = null
    var imageList = arrayOfNulls<Bitmap>(1)

    private val PICK_FROM_ALBUM = 1

    private val photoURI: Uri? = null

    protected fun goToAlbum() {
        tedPermission()

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_FROM_ALBUM) {
            val photoUri = data!!.data
            var cursor: Cursor? = null
            try {
                val proj =
                    arrayOf(MediaStore.Images.Media.DATA)
                assert(photoUri != null)
                cursor = contentResolver.query(photoUri!!, proj, null, null, null)
                assert(cursor != null)
                val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                cursor.moveToFirst()
                tempFile = File(cursor.getString(column_index))
            } catch (e: Exception) {
                Log.d("TAG", e.message + "")
            } finally {
                cursor?.close()
            }
            setImage()
        }
    }

    private fun setImage() {
        val options = BitmapFactory.Options()
        val bitmap = BitmapFactory.decodeFile(tempFile!!.absolutePath, options)
        Log.d("LOG", tempFile!!.absolutePath) // -> 파일 경로
        imageList[0] = bitmap
        file = File(tempFile!!.absolutePath)
//        binding.houseImage.setImageBitmap(imageList[0])
    }

    protected abstract fun requestNotOkEvent()

    protected abstract fun pickNextEvent(data: Intent)

    protected abstract fun cropNextEvent()
}