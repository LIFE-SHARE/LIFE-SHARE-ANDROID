package com.example.lifeshare_android.util;

import com.example.lifeshare_android.BuildConfig;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class UtilsJava {

    public static Retrofit RETROFIT =
            new Retrofit.Builder()
                    .client(getClient())
                    .baseUrl(Strings.MAIN_HOST)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .callbackExecutor(Executors.newSingleThreadExecutor())
                    .build();

    private static OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG)
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return builder.build();
    }
}
