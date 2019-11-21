package com.example.lifeshare_android.base.base_java;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import androidx.lifecycle.ViewModelProviders;

public abstract class BaseActivityJava<VB extends ViewDataBinding> extends AppCompatActivity {

    protected VB binding;

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        binding = null;
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            super.setRequestedOrientation(requestedOrientation);
        }
    }

    protected void startActivity(Class activity) {
        startActivity(new Intent(this, activity));
    }

    protected void startActivityWithFinish(Class activity) {
        startActivity(new Intent(this, activity));
        finish();
    }

    protected void simpleToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void simpleToast(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
