package com.example.lifeshare_android.network.api;

import com.example.lifeshare_android.network.request.AddHouseRequest;

import io.reactivex.Single;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddHouseApiJava {

    @Multipart
    @POST("/house")
    Call<AddHouseRequest> postHouse(
            @Header("x-access-token") String token,
            @Part("name")RequestBody houseNameText,
            @Part("address") RequestBody address,
            @Part("genderLimit") RequestBody genderLimit,
            @Part("ageLimit") RequestBody ageLimit,
            @Part("contractperiod") RequestBody contractperiod,
            @Part("maxMember") RequestBody maxMember,
            @Part("information") RequestBody information,
            @Part MultipartBody.Part image
    );
}
