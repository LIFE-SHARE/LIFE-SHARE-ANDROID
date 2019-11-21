package com.example.lifeshare_android.network.api;

import com.example.lifeshare_android.network.request.AddRoomRequest;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AddRoomApi {

    @Multipart
    @POST("/house")
    Call<AddRoomRequest> postRoom(
            @Header("x-access-token") String token,
            @Part("houseId") RequestBody houseId,
            @Part("people_count") RequestBody peopleCnt,
            @Part("money") RequestBody money,
            @Part MultipartBody.Part image
    );
}
