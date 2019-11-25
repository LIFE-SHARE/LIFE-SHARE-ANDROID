package com.example.lifeshare_android.widget

import android.net.Uri

import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.BindingAdapter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

import com.example.lifeshare_android.R

import net.gahfy.mvvmposts.utils.extension.getParentActivity

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("mutableImageUrl")
fun setMutableImageUrl(view: ImageView, url: MutableLiveData<String>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    val requestOptions: RequestOptions by lazy {
        RequestOptions()
            .error(R.drawable.none_image)
            .transforms(CenterCrop())
    }

    if(url != null) {
        url.observe(parentActivity, Observer { value -> Glide.with(view.context)
            .load(value)
            .apply(requestOptions)
            .into(view)})
    }
    else {
        Glide.with(view.context)
            .load(R.drawable.none_image)
            .into(view)
    }
}

@BindingAdapter("mutableImageUri")
fun setMutableImageUri(view: ImageView, uri: MutableLiveData<Uri>?) {
    val parentActivity: AppCompatActivity = view.getParentActivity() ?: return

    val requestOptions: RequestOptions by lazy {
        RequestOptions()
            .error(R.drawable.none_image)
            .transforms(CenterCrop())
    }

    if(uri != null) {
        uri.observe(parentActivity, Observer { value -> Glide.with(view.context)
            .load(value)
            .apply(requestOptions)
            .into(view)})
    }
    else {
        Glide.with(view.context)
            .load(R.drawable.none_image)
            .into(view)
    }
}