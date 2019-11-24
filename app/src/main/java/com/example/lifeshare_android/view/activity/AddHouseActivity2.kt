package com.example.lifeshare_android.view.activity

import android.content.Intent

import android.graphics.Bitmap

import android.net.Uri

import androidx.lifecycle.Observer

import com.example.lifeshare_android.BR
import com.example.lifeshare_android.R
import com.example.lifeshare_android.base.activity.BasePictureActivity
import com.example.lifeshare_android.database.sharedpreference.Token
import com.example.lifeshare_android.databinding.ActivityAddHouseBinding
import com.example.lifeshare_android.network.api.AddHouseApi
import com.example.lifeshare_android.util.UtilsJava
import com.example.lifeshare_android.viewmodel.AddHouseViewModel

import okhttp3.MediaType.Companion.parse
import okhttp3.MultipartBody
import okhttp3.RequestBody

import java.io.File

class AddHouseActivity2 : BasePictureActivity<ActivityAddHouseBinding, AddHouseViewModel>() {

    var addHouseApi = UtilsJava.RETROFIT.create(AddHouseApi::class.java)

    override fun getLayoutId(): Int {
        return R.layout.activity_add_house
    }

    override fun getViewModel(): Class<AddHouseViewModel> {
        return AddHouseViewModel::class.java
    }

    override fun getBindingVariable(): Int {
        return BR.viewModel
    }

    override fun initObserver() {
        with(viewModel) {

            addImageEvent.observe(this@AddHouseActivity2, Observer {
                goToAlbum()
            })

            addHouseEvent.observe(this@AddHouseActivity2, Observer {
//                val reqImage: RequestBody = RequestBody.create(parse("image/*"), file)
//                val part: MultipartBody.Part = MultipartBody.Part.createFormData("image", file.getName(), reqImage)
//
//                val houseNameText: RequestBody = RequestBody.create(parse.("text/plain"), binding.houseNameText.text.toString())
//                val housePlaceText: RequestBody = RequestBody.create(parse.("text/plain"), binding.housePlaceText.text.toString())
//                val genderText: RequestBody = RequestBody.create(parse.("text/plain"), binding.genderText.text.toString())
//                val ageLimitText: RequestBody = RequestBody.create(parse.("text/plain"), binding.ageLimitText.text.toString())
//                val contractperiodText: RequestBody = RequestBody.create(parse.("text/plain"), binding.contractperiodText.text.toString())
//                val maxMemberText: RequestBody = RequestBody.create(parse.("text/plain"), binding.maxMemberText.text.toString())
//                val infoText: RequestBody = RequestBody.create(parse.("text/plain"), binding.infoText.text.toString())

                val token = Token(applicationContext)

//            Call<> houseRequestCall = addHouseApi.postHouse(token.getToken(),
//                    maxMemberText, houseNameText, housePlaceText, genderText, ageLimitText, contractperiodText, infoText, part);

//            houseRequestCall.enqueue(new Callback<>() {
//                @Override
//                public void onResponse(Call<> call, Response<> response) {
//                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), "등록 오류", Toast.LENGTH_SHORT).show();
//                }
//            });
            })
        }
    }

    override fun requestNotOkEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pickNextEvent(data: Intent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cropNextEvent() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}