package com.example.lifeshare_android.view.activity;

import android.Manifest;

import android.content.Intent;

import android.database.Cursor;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;

import android.os.Bundle;

import android.provider.MediaStore;

import android.util.Log;

import android.widget.Toast;

import com.example.lifeshare_android.R;
import com.example.lifeshare_android.base.base_java.BaseActivityJava;
import com.example.lifeshare_android.database.sharedpreference.Token;
import com.example.lifeshare_android.databinding.ActivityAddRoomBinding;
import com.example.lifeshare_android.network.api.AddRoomApi;
import com.example.lifeshare_android.network.request.AddRoomRequest;
import com.example.lifeshare_android.util.UtilsJava;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;

import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddRoomActivity extends BaseActivityJava<ActivityAddRoomBinding> {

    String houseId;

    File tempFile;
    File file;
    Bitmap[] imageList = new Bitmap[1];

    private final int PICK_FROM_ALBUM = 1;

    private Uri photoURI;

    AddRoomApi addRoomApi = UtilsJava.RETROFIT.create(AddRoomApi.class);

    @Override
    protected int getLayoutId() {

        return R.layout.activity_add_room;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        binding.addRoomBtn.setOnClickListener(v -> {

            RequestBody reqImage = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), reqImage);

            RequestBody houseIdText = RequestBody.create(MediaType.parse("text/plain"), houseId);
            RequestBody memberCountText = RequestBody.create(MediaType.parse("text/plain"), binding.memberCountText.getText().toString());
            RequestBody moneyText = RequestBody.create(MediaType.parse("text/plain"), binding.moneyText.getText().toString());

            Token token = new Token(this);

            Call<AddRoomRequest> roomRequestCall = addRoomApi.postRoom(token.getToken(),
                    houseIdText, memberCountText, moneyText, part);

            roomRequestCall.enqueue(new Callback<AddRoomRequest>() {
                @Override
                public void onResponse(Call<AddRoomRequest> call, Response<AddRoomRequest> response) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }

                @Override
                public void onFailure(Call<AddRoomRequest> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "등록 오류", Toast.LENGTH_SHORT).show();
                }
            });
        });

        clickEvent();
    }

    private void initData() {
        Intent intent = getIntent();
        houseId = intent.getStringExtra("houseId");
    }

    private void clickEvent() {

        binding.inputImg.setOnClickListener(v -> goToAlbum());
    }

    private void goToAlbum() {

        tedPermission();

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_ALBUM) {
            Uri photoUri = data.getData();

            Cursor cursor = null;
            try {
                String[] proj = {MediaStore.Images.Media.DATA};
                assert photoUri != null;
                cursor = getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } catch (Exception e) {
                Log.d("TAG", e.getMessage() + "");
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();
        }
    }

    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                Toast.makeText(getApplicationContext(), "접근을 허용해야 사진을 등록할 수 있습니다", Toast.LENGTH_LONG).show();
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    private void setImage() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);
        Log.d("LOG", tempFile.getAbsolutePath()); // -> 파일 경로

        imageList[0] = bitmap;

        file = new File(tempFile.getAbsolutePath());

        binding.houseImage.setImageBitmap(imageList[0]);
    }
}