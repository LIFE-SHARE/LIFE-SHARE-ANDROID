package com.example.lifeshare_android.base.base_java;

import android.util.Log;

import com.example.lifeshare_android.network.response.Response;
import com.example.lifeshare_android.util.UtilsJava;

import org.json.JSONObject;

import java.util.Objects;

import io.reactivex.functions.Function;


public abstract class BaseClientJava<V> {

    protected V api;

    protected abstract Class api();

    public BaseClientJava() {
        api = (V) UtilsJava.RETROFIT.create(api());
    }

    protected <T> Function<retrofit2.Response<Response<T>>, T> getResponseObjectsFunction() {
        return response -> {
                    if (!response.isSuccessful()) {
                        JSONObject errorBody = new JSONObject(Objects
                                .requireNonNull(
                                        response.errorBody()).string());
                        Log.e("message", errorBody.getString("message"));
                        throw new Exception(errorBody.getString("message"));
                    }
                    return (T) response.body().getData();
                };
    }

}
