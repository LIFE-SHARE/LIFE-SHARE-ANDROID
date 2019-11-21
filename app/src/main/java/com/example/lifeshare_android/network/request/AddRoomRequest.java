package com.example.lifeshare_android.network.request;

public class AddRoomRequest {

    int houseId;
    int peopleCnt;
    String money;
    String imageData;

    public AddRoomRequest(int houseId, int peopleCnt, String money, String imageData) {
        this.houseId = houseId;
        this.peopleCnt = peopleCnt;
        this.money = money;
        this.imageData = imageData;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public int getPeopleCnt() {
        return peopleCnt;
    }

    public void setPeopleCnt(int peopleCnt) {
        this.peopleCnt = peopleCnt;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}