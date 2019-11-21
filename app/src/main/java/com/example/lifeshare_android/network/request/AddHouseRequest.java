package com.example.lifeshare_android.network.request;

public class AddHouseRequest {

    String userId;
    String name;
    String address;
    String genderLimit;
    int ageLimit;
    String contractperiod;
    int maxMember;
    String information;
    String image;

    public AddHouseRequest(String userId, String name, String address, String genderLimit, int ageLimit, String contractperiod, int maxMember, String information, String image) {
        this.userId = userId;
        this.name = name;
        this.address = address;
        this.genderLimit = genderLimit;
        this.ageLimit = ageLimit;
        this.contractperiod = contractperiod;
        this.maxMember = maxMember;
        this.information = information;
        this.image = image;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(int maxMember) {
        this.maxMember = maxMember;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGenderLimit() {
        return genderLimit;
    }

    public void setGenderLimit(String genderLimit) {
        this.genderLimit = genderLimit;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getContractperiod() {
        return contractperiod;
    }

    public void setContractperiod(String contractperiod) {
        this.contractperiod = contractperiod;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
