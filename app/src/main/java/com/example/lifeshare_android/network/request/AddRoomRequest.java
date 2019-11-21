package com.example.lifeshare_android.network.request;

public class AddRoomRequest {

    int house_id;
    int peopleCnt;
    String money;
    String imageData;

    public AddRoomRequest(int house_id, int peopleCnt, String money, String imageData) {
        this.house_id = house_id;
        this.peopleCnt = peopleCnt;
        this.money = money;
        this.imageData = imageData;
    }

    public int getHouse_id() {
        return house_id;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
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